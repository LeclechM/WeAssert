package fr.istic.gm.weassert.test.generator;

import fr.istic.gm.weassert.test.generator.impl.SourceCodeWriter;
import fr.istic.gm.weassert.test.utils.BackupUtils;
import fr.istic.gm.weassert.test.utils.impl.BackupUtilsImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static fr.istic.gm.weassert.TestUtils.getAbsolutePath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class SourceCodeWriterTest {
    private SourceCodeWriter sourceCodeWriter;
    private String classPath;
    private BackupUtils backupUtils;

    @Before
    public void setUp() {
        this.classPath = getAbsolutePath("fake/src/test/java/fr/istic/gm/weassert/fake/PersonTest.java");

        backupUtils = new BackupUtilsImpl(classPath);
        this.sourceCodeWriter = new SourceCodeWriter(this.classPath);
    }

    @After
    public void tearDown() {
        backupUtils.restore();
    }

    @Test
    public void shouldConstruct() {
        assertNotNull(this.sourceCodeWriter.getClassName());
    }

    @Test
    public void insertOne() {
        String code = "System.out.println(\"Hello World !\");";
        this.sourceCodeWriter.insertOne("testAge", "()V", code);


        assertTrue(this.sourceCodeWriter.getSourceCode().contains(code));
    }

    @Test
    public void writeAndCloseFile() {
        String code = "System.out.println(\"Hello World !\");";
        this.sourceCodeWriter.insertOne("testAge", "()V", code);
        this.sourceCodeWriter.writeAndCloseFile();

        try {
            String readFile = new String(Files.readAllBytes(Paths.get(this.classPath)));
            assertEquals(this.sourceCodeWriter.getSourceCode(), readFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}