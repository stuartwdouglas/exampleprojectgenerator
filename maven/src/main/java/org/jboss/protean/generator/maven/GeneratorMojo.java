package io.quarkus.generator.maven;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

@Mojo(name = "run", defaultPhase = LifecyclePhase.PROCESS_CLASSES)
public class GeneratorMojo extends AbstractMojo {

    /**
     * The directory for generated sources.
     */
    @Parameter(readonly = true, required = true, defaultValue = "${project.build.directory}/generated-sources")
    private File generatedSourcesDirectory;


    @Parameter(readonly = true, required = true, defaultValue = "${project.basedir}/src/main/templates")
    private File templatesDir;

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    protected MavenProject project;

    /**
     * The templates
     */
    @Parameter
    private List<String> templates;

    @Parameter(defaultValue = "10")
    private int copies;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        File output = new File(generatedSourcesDirectory, "com" + File.separator + "test" + File.separator);
        output.mkdirs();
        project.addCompileSourceRoot(generatedSourcesDirectory.getAbsolutePath());
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
            cfg.setDirectoryForTemplateLoading(templatesDir);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            generatedSourcesDirectory.mkdirs();
            for (String template : templates) {
                String baseName = template.substring(0, template.lastIndexOf('.'));

                for (int i = 0; i < copies; ++i) {
                    Map root = new HashMap();
                    root.put("no", "" + i);

                    /* Get the template (uses cache internally) */
                    Template temp = cfg.getTemplate(template);

                    /* Merge data-model with template */
                    try (Writer out = new OutputStreamWriter(new FileOutputStream(new File(output, baseName + i + ".java")))) {
                        temp.process(root, out);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
