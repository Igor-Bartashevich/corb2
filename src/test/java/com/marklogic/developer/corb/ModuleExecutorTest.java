package com.marklogic.developer.corb;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.junit.*;
import static org.junit.Assert.*;

import com.marklogic.developer.Utilities;
import com.marklogic.developer.corb.ModuleExecutor;
import com.marklogic.xcc.ContentSource;
import com.marklogic.xcc.ContentSourceFactory;
import com.marklogic.xcc.Request;
import com.marklogic.xcc.RequestOptions;
import com.marklogic.xcc.ResultSequence;
import com.marklogic.xcc.SecurityOptions;
import com.marklogic.xcc.Session;
import com.marklogic.xcc.exceptions.RequestException;
import com.marklogic.xcc.exceptions.XccConfigException;
import com.marklogic.xcc.jndi.ContentSourceBean;
import com.marklogic.xcc.types.ItemType;
import com.marklogic.xcc.types.XdmItem;
import com.marklogic.xcc.types.impl.AbstractStreamableItem;

/**
 * The class <code>ModuleExecutorTest</code> contains tests for the class <code>{@link ModuleExecutor}</code>.
 *
 * @generatedBy CodePro at 9/18/15 12:45 PM
 * @author matthew.heckel
 * @version $Revision: 1.0 $
 */
public class ModuleExecutorTest {
	
private static final String propertyFileLocation = "src\\test\\resources\\helloWorld.properties";

	/**
	 * Run the ModuleExecutor(URI) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testModuleExecutor_1()
		throws Exception {
		clearProperties();
		Properties props = getProperties();
		URI connectionUri = new URI(props.getProperty("XCC-CONNECTION-URI"));
		
		ModuleExecutor result = new ModuleExecutor(connectionUri);

		assertNotNull(result);
	}

	private Properties loadProperties(URL filePath) {
		InputStream input = null;
		Properties prop = new Properties();
		try {	
			input = filePath.openStream();
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	/**
	 * Run the ContentSource getContentSource() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testGetContentSource_1()
		throws Exception {
		clearProperties();
		ModuleExecutor executor = this.buildModuleExecutorAndLoadProperties();
		executor.contentSource = new ContentSourceBean();
		executor.options = new TransformOptions();

		ContentSource result = executor.getContentSource();

		assertNotNull(result);
	}

	/**
	 * Run the String getOption(String,String,Properties) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testGetOption_1()
		throws Exception {
		clearProperties();
		String argVal = "";
		String propName = "URIS-MODULE";
		ModuleExecutor executor = this.buildModuleExecutorAndLoadProperties();

		String result = ModuleExecutor.getOption(argVal, propName, executor.getProperties());
		
		assertNotNull(result);
	}

	/**
	 * Run the String getOption(String,String,Properties) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testGetOption_2()
		throws Exception {
		clearProperties();
		System.setProperty("URIS-MODULE", "helloWorld-selector.xqy");
		String argVal = "";
		String propName = "URIS-MODULE";
		Properties props = new Properties();
		
		String result = ModuleExecutor.getOption(argVal, propName, props);

		assertNotNull(result);
	}

	/**
	 * Run the String getOption(String,String,Properties) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testGetOption_3()
		throws Exception {
		clearProperties();
		String argVal = "URIS-MODULE";
		String propName = "";
		Properties props = new Properties();

		String result = ModuleExecutor.getOption(argVal, propName, props);

		assertNotNull(result);
	}

	/**
	 * Run the TransformOptions getOptions() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testGetOptions_1()
		throws Exception {
		clearProperties();
		ModuleExecutor executor = this.buildModuleExecutorAndLoadProperties();
		
		executor.contentSource = new ContentSourceBean();
		executor.options = new TransformOptions();

		TransformOptions result = executor.getOptions();

		assertNotNull(result);
	}

	/**
	 * Run the Properties getProperties() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testGetProperties_1()
		throws Exception {
		clearProperties();
		ModuleExecutor executor = this.buildModuleExecutorAndLoadProperties();
		executor.contentSource = new ContentSourceBean();
		executor.options = new TransformOptions();

		Properties result = executor.getProperties();

		assertNotNull(result);
	}

	/**
	 * Run the String getProperty(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testGetProperty_1()
		throws Exception {
		clearProperties();
		System.setProperty("systemProperty", "hellowWorld");
		ModuleExecutor executor = this.buildModuleExecutorAndLoadProperties();
		executor.contentSource = new ContentSourceBean();
		executor.options = new TransformOptions();
		String key = "systemProperty";

		String result = executor.getProperty(key);

		assertNotNull(result);
	}

	/**
	 * Run the String getProperty(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testGetProperty_2()
		throws Exception {
		clearProperties();
		ModuleExecutor executor = this.buildModuleExecutorAndLoadProperties();
		executor.contentSource = new ContentSourceBean();
		executor.options = new TransformOptions();
		String key = "PROCESS-TASK";

		String result = executor.getProperty(key);

		assertNotNull(result);
	}

	/**
	 * Run the byte[] getValueAsBytes(XdmItem) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testGetValueAsBytes_1()
		throws Exception {
		clearProperties();
		System.setProperty("EXPORT-FILE-NAME","src\\test\\resources\\testGetValueAsBytes_1.txt");
		System.setProperty("OPTIONS-FILE","src\\test\\resources\\helloWorld.properties");
		System.setProperty("XQUERY-MODULE","src\\test\\resources\\transform2.xqy|ADHOC");
		Properties props = getProperties();
		String[] args = {props.getProperty("XCC-CONNECTION-URI")};
		ModuleExecutor executor = ModuleExecutor.createExecutor(args);
		ResultSequence resSeq = run(executor);
		byte[] report = executor.getValueAsBytes(resSeq.next().getItem());
		
		assertNotNull(report);
	}

	private String getPropertyFileLocation() {
		// TODO Auto-generated method stub
		return this.propertyFileLocation;
	}

	/**
	 * Run the void main(String[]) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testMain_1()
		throws Exception {
		clearProperties();
		System.setProperty("OPTIONS-FILE","src\\test\\resources\\helloWorld.properties");
		System.setProperty("XQUERY-MODULE","src\\test\\resources\\transform2.xqy|ADHOC");
		System.setProperty("EXPORT-FILE-NAME","src\\test\\resources\\helloWorld.txt");
		String[] args = new String[] {};

		ModuleExecutor.main(args);
		
		File report = new File("src\\test\\resources\\helloWorld.txt");
		boolean fileExists = report.exists();
        clearFile(report);
		assertTrue(fileExists);
	}

	/**
	 * Run the SecurityOptions newTrustAnyoneOptions() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testNewTrustAnyoneOptions_1()
		throws Exception {

		SecurityOptions result = ModuleExecutor.newTrustAnyoneOptions();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getEnabledProtocols());
		assertEquals(null, result.getEnabledCipherSuites());
	}

	/**
	 * Run the void prepareContentSource() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testPrepareContentSource() throws Exception {
		clearProperties();
		ModuleExecutor executor = this.buildModuleExecutorAndLoadProperties();

		executor.prepareContentSource();
		assertNotNull(executor.contentSource);

	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testRun_1()
		throws Exception {
		clearProperties();
		System.setProperty("OPTIONS-FILE","src\\test\\resources\\helloWorld.properties");
		System.setProperty("XQUERY-MODULE","src\\test\\resources\\transform2.xqy|ADHOC");
		System.setProperty("EXPORT-FILE-NAME","src\\test\\resources\\helloWorld.txt");
		String[] args = {};
		ModuleExecutor executor = ModuleExecutor.createExecutor(args);
		executor.run();
		
		String reportPath = executor.getProperty("EXPORT-FILE-NAME");
		File report = new File(reportPath);
		boolean fileExists = report.exists();
		clearFile(report);
		assertTrue(fileExists);

	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testRun_2()
		throws Exception {
		clearProperties();
		String[] args = {
				"xcc://admin:admin@localhost:2223/FFE",
				"src\\test\\resources\\transform2.xqy|ADHOC",
				"",
				"",
				"com.marklogic.developer.corb.ExportBatchToFileTask",
				"",
				"src\\test\\resources\\helloWorld.txt"
				};
		ModuleExecutor executor = ModuleExecutor.createExecutor(args);
		executor.run();
		
		String reportPath = executor.getProperty("EXPORT-FILE-NAME");
		File report = new File(reportPath);
		boolean fileExists = report.exists();
		clearFile(report);
		assertTrue(fileExists);
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testRun_3()
		throws Exception {
		clearProperties();
		String[] args = {};
		System.setProperty("XCC-CONNECTION-URI","xcc://admin:admin@localhost:2223/FFE");
		System.setProperty("XQUERY-MODULE","src\\test\\resources\\transform2.xqy|ADHOC");
		System.setProperty("DECRYPTER","com.marklogic.developer.corb.JasyptDecrypter");
		System.setProperty("JASYPT-PROPERTIES-FILE", "src\\test\\resources\\jasypt.properties");
		System.setProperty("PRE-BATCH-TASK","com.marklogic.developer.corb.PreBatchUpdateFileTask");
		System.setProperty("EXPORT-FILE-NAME","src\\test\\resources\\helloWorld.txt");
		System.setProperty("PROCESS-TASK","com.marklogic.developer.corb.ExportBatchToFileTask");
		
		ModuleExecutor executor = ModuleExecutor.createExecutor(args);
		executor.run();
		
		String reportPath = executor.getProperty("EXPORT-FILE-NAME");
		File report = new File(reportPath);
		boolean fileExists = report.exists();
		clearFile(report);
		assertTrue(fileExists);
	}

		/**
	 * Run the void setProperties(Properties) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Test
	public void testSetProperties_1()
		throws Exception {
		clearProperties();
		System.setProperty("OPTIONS-FILE", "src\\test\\resources\\helloWorld.properties");
		ModuleExecutor executor = this.buildModuleExecutorAndLoadProperties();
		Properties props = executor.getProperties();
		
		assertNotNull(props);
		if (props != null) {
			assertFalse(props.isEmpty());
		}
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 9/18/15 12:45 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ModuleExecutorTest.class);
	}
	
	private Properties getProperties() {
			
		String propFileLocation = System.getProperty("OPTIONS-FILE");
		if (propFileLocation == null || propFileLocation.length() == 0) {
			propFileLocation = this.propertyFileLocation;
		}
		File propFile = new File(propFileLocation);
		URL url = null;
		try {
			url = propFile.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loadProperties(url);
	}
	
	private ModuleExecutor buildModuleExecutorAndLoadProperties() {
		ModuleExecutor executor = null;
		Properties props = getProperties();
		try {
			executor = new ModuleExecutor(new URI(props.getProperty("XCC-CONNECTION-URI")));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.setProperties(props);
		
	    return executor;	
	
	}
	
	private ResultSequence run(ModuleExecutor executor) throws CorbException {

		executor.prepareContentSource();
	    Session session = null;
        ResultSequence res = null;
		try {
			RequestOptions opts = new RequestOptions();
			opts.setCacheResult(false);
			session = executor.contentSource.newSession();
			Request req = null;
			TransformOptions options = executor.getOptions();
			Properties properties = executor.getProperties();
			
			List<String> propertyNames = new ArrayList<String>(
					properties.stringPropertyNames());
			propertyNames.addAll(System.getProperties().stringPropertyNames());
			
			String queryPath = options.getProcessModule().substring(0,
			options.getProcessModule().indexOf('|'));

			String adhocQuery = executor.getAdhocQuery(queryPath);
			if (adhocQuery == null || (adhocQuery.length() == 0)) {
					throw new IllegalStateException(
								"Unable to read adhoc query " + queryPath
										+ " from classpath or filesystem");
			}
			req = session.newAdhocQuery(adhocQuery);
			for (String propName : propertyNames) {
				if (propName.startsWith("XQUERY-MODULE.")) {
					String varName = propName.substring("XQUERY-MODULE.".length());
						String value = properties.getProperty(propName);
						if (value != null)
							req.setNewStringVariable(varName, value);
					}
				}
			req.setOptions(opts);
			res = session.submitRequest(req);

		} catch (RequestException exc) {
			throw new CorbException("While invoking XQuery Module", exc);
		}  catch (Exception exd) {
			throw new CorbException("While invoking XCC...", exd);
		}
		return res;
	}
	
	private void clearProperties() {
		System.clearProperty("URIS-MODULE");
		System.clearProperty("OPTIONS-FILE");
		System.clearProperty("XCC-CONNECTION-URI");
		System.clearProperty("COLLECTION-NAME"); 
		System.clearProperty("XQUERY-MODULE");
		System.clearProperty("THREAD-COUNT");
		System.clearProperty("MODULE-ROOT");
		System.clearProperty("MODULES-DATABASE"); 
		System.clearProperty("INSTALL");
		System.clearProperty("PROCESS-TASK");
		System.clearProperty("PRE-BATCH-MODULE");
		System.clearProperty("PRE-BATCH-TASK");
		System.clearProperty("POST-BATCH-MODULE");
		System.clearProperty("POST-BATCH-TASK");
		System.clearProperty("EXPORT-FILE-DIR"); 
		System.clearProperty("EXPORT-FILE-NAME");
		System.clearProperty("URIS-FILE");
		System.clearProperty("XQUERY-MODULE.foo");
		System.clearProperty("EXPORT_FILE_AS_ZIP");
	}
	
	private void clearFile(File file) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (pw != null) {
			pw.close();	
		}
	}
}