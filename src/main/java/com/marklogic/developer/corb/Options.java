/*
  * * Copyright (c) 2004-2016 MarkLogic Corporation
  * *
  * * Licensed under the Apache License, Version 2.0 (the "License");
  * * you may not use this file except in compliance with the License.
  * * You may obtain a copy of the License at
  * *
  * * http://www.apache.org/licenses/LICENSE-2.0
  * *
  * * Unless required by applicable law or agreed to in writing, software
  * * distributed under the License is distributed on an "AS IS" BASIS,
  * * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * * See the License for the specific language governing permissions and
  * * limitations under the License.
  * *
  * * The use of the Apache License does not indicate that this project is
  * * affiliated with the Apache Software Foundation.
 */
package com.marklogic.developer.corb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Options that allow users to configure CoRB and control various aspects of
 * execution.
 *
 * @author Mads Hansen, MarkLogic Corporation
 * @since 2.3.0
 */
public final class Options {

    /**
     * The number of URIs to be executed in single transform.
     * <p>
     * Default value is 1. If greater than 1, the {@value #PROCESS_MODULE} will
     * receive a delimited string as the URI variable value, which needs to be
     * tokenized to get the individual URIs.
     * </p><p>
     * The default delimiter is {@code ;}, which can be overridden with the
     * option {@value #BATCH_URI_DELIM}.
     * </p>
     * Sample code for transform:
     * <pre><code>
     * declare variable URI as xs:string exernal;
     * let $all-uris := fn:tokenize($URI,";")
     * </code></pre>
     */
    @Usage(description = "The number of uris to be executed in single transform. "
            + "Default value is 1. If greater than 1, the PROCESS-MODULE will "
            + "receive a delimited string as the $URI variable value, which needs "
            + "to be tokenized to get individual URIs. The default delimiter is `;`, "
            + "which can be overridden with the option BATCH-URI-DELIM.")
    public static final String BATCH_SIZE = "BATCH-SIZE";

    /**
     * Use if the default delimiter "{@code ;}" cannot be used to join multiple
     * URIS when {@value #BATCH_SIZE} is greater than 1.
     */
    @Usage(description = "Use if the default delimiter ';' cannot be used to join "
            + "multiple URIS when BATCH-SIZE is greater than 1.")
    public static final String BATCH_URI_DELIM = "BATCH-URI-DELIM";

    /**
     * Value of this parameter will be passed into the {@value #URIS_MODULE} via
     * external or global variable with the name URIS.
     */
    @Usage(description = "Value of this parameter will be passed into the URIS-MODULE "
            + "via external or global variable with the name URIS.")
    public static final String COLLECTION_NAME = "COLLECTION-NAME";

    /**
     * Pause, resume, and stop the execution of CoRB2.
     * <p>
     * Possible commands include: PAUSE, RESUME, and STOP.
     * <p>
     * If the {@value #COMMAND_FILE} is modified and either there is no COMMAND
     * or an invalid value is specified, then execution will RESUME.
     *
     * @since 2.3.0
     */
    @Usage(description = "Pause, resume, and stop the execution of CoRB2. "
            + "Possible commands include: PAUSE, RESUME, and STOP. "
            + "If the COMMAND-FILE is modified and either there is no COMMAND or "
            + "an invalid value is specified, then execution will RESUME.")
    public static final String COMMAND = "COMMAND";

    /**
     * A properties file used to configure {@value #COMMAND} and
     * {@value #THREAD_COUNT} while CoRB2 is running.
     * <p>
     * For instance, to temporarily pause execution, or to lower the number of
     * threads in order to throttle execution.
     *
     * @since 2.3.0
     */
    @Usage(description = "A properties file used to configure COMMAND and THREAD-COUNT while CoRB2 is running. "
            + "For instance, to temporarily pause execution, or to lower the number of threads in order to throttle execution.")
    public static final String COMMAND_FILE = "COMMAND-FILE";

    /**
     * The regular interval (seconds) in which the existence of the
     * {@value #COMMAND_FILE} is tested can be controlled by using this
     * property. Default is 1.
     *
     * @since 2.3.0
     */
    @Usage(description = "The regular interval (seconds) in which the existence of "
            + "the COMMAND-FILE is tested can be controlled by using this property. "
            + "Default is 1.")
    public static final String COMMAND_FILE_POLL_INTERVAL = "COMMAND-FILE-POLL-INTERVAL";

    /**
     * The class name of the options value decrypter, which must implement
     * {@link com.marklogic.developer.corb.Decrypter}.
     * <p>
     * Encryptable options include {@value #XCC_CONNECTION_URI}, {@value #XCC_USERNAME},
     * {@value #XCC_PASSWORD}, {@value #XCC_HOSTNAME}, {@value #XCC_PORT}, and
     * {@value #XCC_DBNAME}
     * </p>
     * <ul>
     * <li>{@link com.marklogic.developer.corb.PrivateKeyDecrypter} (Included)
     * Requires private key file</li>
     * <li>{@link com.marklogic.developer.corb.JasyptDecrypter} (Included)
     * Requires jasypt-*.jar in classpath</li>
     * <li>{@link com.marklogic.developer.corb.HostKeyDecrypter} (Included)
     * Requires Java Cryptography Extension (JCE) Unlimited Strength
     * Jurisdiction Policy Files</li>
     * </ul>
     *
     * @see com.marklogic.developer.corb.Decrypter
     * @see com.marklogic.developer.corb.AbstractDecrypter
     * @see #PRIVATE_KEY_ALGORITHM
     * @see #PRIVATE_KEY_FILE
     */
    @Usage(description = "The class name of the options value decrypter, which must "
            + "implement com.marklogic.developer.corb.Decrypter. Encryptable options "
            + "include XCC-CONNECTION-URI, XCC-USERNAME, XCC-PASSWORD, XCC-HOSTNAME, XCC-PORT, and XCC-DBNAME. "
            + "com.marklogic.developer.corb.PrivateKeyDecrypter (Included) Requires private key file. "
            + "com.marklogic.developer.corb.JasyptDecrypter (Included) Requires jasypt-*.jar in classpath. "
            + "com.marklogic.developer.corb.HostKeyDecrypter (Included) Requires "
            + "Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files.")
    public static final String DECRYPTER = "DECRYPTER";

    /**
     * Boolean value indicating whether the CoRB job should spill to disk when a
     * maximum number of URIs have been loaded in memory, in order to control
     * memory consumption and avoid Out of Memory exceptions for extremely large
     * sets of URIs.
     *
     * @since 2.3.1
     * @see #DISK_QUEUE_MAX_IN_MEMORY_SIZE
     * @see #DISK_QUEUE_TEMP_DIR
     */
    @Usage(description = "Boolean value indicating whether the CoRB job should "
            + "spill to disk when a maximum number of URIs have been loaded in "
            + "memory, in order to control memory consumption and avoid Out of "
            + "Memory exceptions for extremely large sets of URIs.")
    public static final String DISK_QUEUE = "DISK-QUEUE";

    /**
     * The maximum number of URIs to hold in memory before spilling over to
     * disk. Default is 1,000.
     *
     * @since 2.3.1
     * @see #DISK_QUEUE
     * @see #DISK_QUEUE_TEMP_DIR
     */
    @Usage(description = "The maximum number of URIs to hold in memory before spilling over to disk. "
            + "Default is 1,000.")
    public static final String DISK_QUEUE_MAX_IN_MEMORY_SIZE = "DISK-QUEUE-MAX-IN-MEMORY-SIZE";

    /**
     * The directory where the URIs queue can write to disk when the maximum
     * in-memory items has been exceeded. Default behavior is to use
     * {@code java.io.tmpdir}.
     *
     * @since 2.3.1
     * @see #DISK_QUEUE
     * @see #DISK_QUEUE_MAX_IN_MEMORY_SIZE
     */
    @Usage(description = "The directory where the URIs queue can write to disk when "
            + "the maximum in-memory items has been exceeded. "
            + "Default behavior is to use java.io.tmpdir.")
    public static final String DISK_QUEUE_TEMP_DIR = "DISK-QUEUE-TEMP-DIR";

    /**
     * Used when {@value #FAIL_ON_ERROR} is {@code false}. If specified true,
     * removes duplicates from, the errored URIs along with error messages will
     * be written to this file. Uses {@value #BATCH_URI_DELIM} or default
     * '{@code ;}' to separate URIs and error messages.
     */
    @Usage(description = "Used when FAIL-ON-ERROR is false. If specified true, "
            + "removes duplicates from, the errored URIs along with error messages "
            + "will be written to this file. "
            + "Uses BATCH-URI-DELIM or default ';' to separate URIs and error messages.")
    public static final String ERROR_FILE_NAME = "ERROR-FILE-NAME";

    /**
     * Default is 0. Returns this exit code when there is nothing to process.
     */
    @Usage(description = "Default is 0. Returns this exit code when there is nothing to process.")
    public static final String EXIT_CODE_NO_URIS = "EXIT-CODE-NO-URIS";

    /**
     * If true, {@link com.marklogic.developer.corb.PostBatchUpdateFileTask}
     * compresses the output file as a zip file.
     */
    @Usage(description = "If true, PostBatchUpdateFileTask compresses the output file as a zip file.")
    public static final String EXPORT_FILE_AS_ZIP = "EXPORT_FILE_AS_ZIP";

    /**
     * Used by {@link com.marklogic.developer.corb.PostBatchUpdateFileTask} to
     * append content to {@value #EXPORT_FILE_NAME} after batch process is
     * complete.
     */
    @Usage(description = "Used by com.marklogic.developer.corb.PostBatchUpdateFileTask "
            + "to append content to EXPORT-FILE-NAME after batch process is complete.")
    public static final String EXPORT_FILE_BOTTOM_CONTENT = "EXPORT-FILE-BOTTOM-CONTENT";

    /**
     * Export directory parameter is used by
     * {@link com.marklogic.developer.corb.ExportBatchToFileTask} or similar
     * custom task implementations.
     * <p>
     * Optional: Alternatively, {@value #EXPORT_FILE_NAME} can be specified with
     * a full path.
     * </p>
     *
     * @see #EXPORT_FILE_NAME
     */
    @Usage(description = "Export directory parameter is used by "
            + "com.marklogic.developer.corb.ExportBatchToFileTask or similar custom task implementations."
            + "Optional: Alternatively, EXPORT-FILE-NAME can be specified with a full path.")
    public static final String EXPORT_FILE_DIR = "EXPORT-FILE-DIR";

    /**
     * Used to track the line count for {@value #EXPORT_FILE_TOP_CONTENT} by
     * {@link com.marklogic.developer.corb.PostBatchUpdateFileTask}.
     */
    @Usage
    public static final String EXPORT_FILE_HEADER_LINE_COUNT = "EXPORT-FILE-HEADER-LINE-COUNT";

    /**
     * Shared file to write output of
     * {@link com.marklogic.developer.corb.ExportBatchToFileTask} - should be a
     * file name with our without full path.
     * <ul>
     * <li>{@value #EXPORT_FILE_DIR} Is not required if a full path is
     * used.</li>
     * <li>If {@value #EXPORT_FILE_NAME} is not specified, CoRB attempts to use
     * {@value #URIS_BATCH_REF} as the file name and this is especially useful
     * in case of automated jobs where file name can only be determined by the
     * {@value #URIS_MODULE} - refer to {@value #URIS_BATCH_REF}.</li>
     * </ul>
     */
    @Usage(description = "Shared file to write output of com.marklogic.developer.corb.ExportBatchToFileTask "
            + "- should be a file name with our without full path."
            + "EXPORT-FILE-DIR Is not required if a full path is used."
            + "If EXPORT-FILE-NAME is not specified, CoRB attempts to use URIS_BATCH_REF "
            + "as the file name and this is especially useful in case of automated "
            + "jobs where file name can only be determined by the URIS-MODULE - refer to URIS_BATCH_REF.")
    public static final String EXPORT_FILE_NAME = "EXPORT-FILE-NAME";

    /**
     * The file extension for export files being processed. For example: ".tmp".
     * <p>
     * If specified, {@link com.marklogic.developer.corb.PreBatchUpdateFileTask}
     * adds this temporary extension to the export file name to indicate
     * {@value #EXPORT_FILE_NAME} is being actively modified. To remove this
     * temporary extension after {@value #EXPORT_FILE_NAME} is complete,
     * {@link com.marklogic.developer.corb.PostBatchUpdateFileTask} must be
     * specified as {@value #POST_BATCH_TASK}.
     */
    @Usage(description = "The file extension for export files being processed. "
            + "ex: .tmp - if specified, com.marklogic.developer.corb.PreBatchUpdateFileTask "
            + "adds this temporary extension to the export file name to indicate "
            + "EXPORT-FILE-NAME is being actively modified. To remove this temporary "
            + "extension after EXPORT-FILE-NAME is complete, com.marklogic.developer.corb.PostBatchUpdateFileTask "
            + "must be specified as POST-BATCH-TASK.")
    public static final String EXPORT_FILE_PART_EXT = "EXPORT-FILE-PART-EXT";

    /**
     * If "{@code ascending}" or "{@code descending}", lines will be sorted. If
     * "{@code |distinct}" is specified after the sort direction, duplicate
     * lines from {@value #EXPORT_FILE_NAME} will be removed. i.e.
     * "{@code ascending|distinct}" or "{@code descending|distinct}"
     *
     * @since 2.2.1
     */
    @Usage(description = "If ascending or descending, lines will be sorted. If '|distinct' "
            + "is specified after the sort direction, duplicate lines from EXPORT-FILE-NAME "
            + "will be removed. i.e. ascending|distinct or descending|distinct")
    public static final String EXPORT_FILE_SORT = "EXPORT-FILE-SORT";

    /**
     * A java class that must implement {@link java.util.Comparator}.
     * <p>
     * If specified, CoRB will use this class for sorting in place of ascending
     * or descending string comparator even if a value was specified for
     * {@value #EXPORT_FILE_SORT}.
     *
     * @since 2.2.1
     */
    @Usage(description = "A java class that must implement java.util.Comparator. "
            + "If specified, CoRB will use this class for sorting in place of ascending "
            + "or descending string comparator even if a value was specified for EXPORT-FILE-SORT.")
    public static final String EXPORT_FILE_SORT_COMPARATOR = "EXPORT-FILE-SORT-COMPARATOR";

    /**
     * Used by {@link com.marklogic.developer.corb.PreBatchUpdateFileTask} to
     * insert content at the top of {@value #EXPORT_FILE_NAME} before batch
     * process starts.
     * <p>
     * If it includes the string "{@code @URIS_BATCH_REF}", it is replaced by
     * the batch reference returned by {@value #URIS_MODULE}.
     */
    @Usage(description = "Used by com.marklogic.developer.corb.PreBatchUpdateFileTask "
            + "to insert content at the top of EXPORT-FILE-NAME before batch process starts. "
            + "If it includes the string @URIS\\_BATCH\\_REF, it is replaced by the "
            + "batch reference returned by URIS-MODULE.")
    public static final String EXPORT_FILE_TOP_CONTENT = "EXPORT-FILE-TOP-CONTENT";

    /**
     * Boolean value indicating whether to convert doc URI to a filepath.
     * Default is true.
     *
     * @since 2.3.0
     */
    @Usage(description = "Boolean value indicating whether to convert doc URI to a filepath. "
            + "Default is true.")
    public static final String EXPORT_FILE_URI_TO_PATH = "EXPORT-FILE-URI-TO-PATH";

    /**
     * Boolean value indicating whether the CoRB job should fail and exit if a
     * process module throws an error.
     * <p>
     * Default is true. This option will not handle repeated connection
     * failures.
     */
    @Usage(description = "Boolean value indicating whether the CoRB job should fail "
            + "and exit if a process module throws an error. Default is true. "
            + "This option will not handle repeated connection failures.")
    public static final String FAIL_ON_ERROR = "FAIL-ON-ERROR";

    /**
     * An XQuery or JavaScript module which, if specified, will be invoked prior
     * to {@value #URIS_MODULE}.
     * <p>
     * XQuery and JavaScript modules need to have "{@code .xqy}" and
     * "{@code .sjs}" extensions respectively.
     */
    @Usage(description = "	An XQuery or JavaScript module which, if specified, "
            + "will be invoked prior to URIS-MODULE. XQuery and JavaScript modules "
            + "need to have .xqy and .sjs extensions respectively.")
    public static final String INIT_MODULE = "INIT-MODULE";

    /**
     * Java Task which, if specified, will be called prior to
     * {@value #URIS_MODULE}. This can be used addition to {@value #INIT_MODULE}
     * for custom implementations.
     *
     * @see com.marklogic.developer.corb.Task
     * @see com.marklogic.developer.corb.AbstractTask
     */
    @Usage(description = "Java Task which, if specified, will be called prior to "
            + "URIS-MODULE. This can be used addition to INIT-MODULE for custom implementations.")
    public static final String INIT_TASK = "INIT-TASK";

    /**
     * Whether to install the Modules in the Modules database. Specify 'true' or
     * '1' for installation. Default is false.
     */
    @Usage(description = "Whether to install the Modules in the Modules database. "
            + "Specify 'true' or '1' for installation. "
            + "Default is false.")
    public static final String INSTALL = "INSTALL";

    /**
     * (Optional) Property file for the
     * {@link com.marklogic.developer.corb.JasyptDecrypter}.
     * <p>
     * If not specified, it uses default jasypt.proeprties file, which should be
     * accessible in the classpath or file system.
     */
    @Usage(description = "(Optional) Property file for the JasyptDecrypter. "
            + "If not specified, it uses default jasypt.proeprties file, "
            + "which should be accessible in the classpath or file system.")
    public static final String JASYPT_PROPERTIES_FILE = "JASYPT-PROPERTIES-FILE";

    /**
     * Default is 10. Max number of custom inputs from the {@value #URIS_MODULE}
     * to other modules.
     */
    @Usage(description = "Default is 10. Max number of custom inputs from the URIS-MODULE to other modules.")
    public static final String MAX_OPTS_FROM_MODULE = "MAX_OPTS_FROM_MODULE";

    /**
     * Uses the {@value #XCC_CONNECTION_URI} if not provided; use 0 for file
     * system.
     */
    @Usage(description = "Uses the XCC-CONNECTION-URI if not provided; use 0 for file system.")
    public static final String MODULES_DATABASE = "MODULES-DATABASE";

    /**
     * Default is '/'.
     */
    @Usage(description = "Default is '/'.")
    public static final String MODULE_ROOT = "MODULE-ROOT";

    /**
     * Default is 10. Max number of recent tps (transaction per second) values
     * used to calculate ETC (estimated time to completion)
     */
    @Usage(description = "Default is 10. Max number of recent tps values used to calculate ETC")
    public static final String NUM_TPS_FOR_ETC = "NUM-TPS-FOR-ETC";

    /**
     * A properties file containing any of the CoRB2 options. Relative and full
     * file system paths are supported.
     */
    @Usage(description = "A properties file containing any of the CoRB2 options. "
            + "Relative and full file system paths are supported.")
    public static final String OPTIONS_FILE = "OPTIONS-FILE";

    /**
     * An XQuery or JavaScript module which, if specified, will be run after
     * batch processing is completed. XQuery and JavaScript modules need to have
     * "{@code .xqy}" and "{@code .sjs}" extensions respectively.
     */
    @Usage(description = "An XQuery or JavaScript module which, if specified, "
            + "will be run after batch processing is completed. XQuery and JavaScript "
            + "modules need to have .xqy and .sjs extensions respectively.")
    public static final String POST_BATCH_MODULE = "POST-BATCH-MODULE";

    /**
     * Java Class that implements {@link com.marklogic.developer.corb.Task} or
     * extends {@link com.marklogic.developer.corb.AbstractTask}.
     * <p>
     * If {@value #POST_BATCH_MODULE} is also specified, the implementation is
     * expected to invoke the XQuery and process the result if any. It can also
     * be specified without {@value #POST_BATCH_MODULE} and an example of this
     * is to add static content to the bottom of the report.
     * <ul>
     * <li>{@link com.marklogic.developer.corb.PostBatchUpdateFileTask}
     * (included) - Writes the data returned by the {@value #POST_BATCH_MODULE}
     * to {@value #EXPORT_FILE_NAME}. Also, if
     * {@value #EXPORT_FILE_BOTTOM_CONTENT} is specified, this task will write
     * this value to the {@value #EXPORT_FILE_NAME}. If
     * {@value #EXPORT_FILE_NAME} is not specified, CoRB uses
     * {@value #URIS_BATCH_REF} returned by {@value #URIS_MODULE} as the file
     * name.
     * </li>
     * </ul>
     *
     * @see com.marklogic.developer.corb.Task
     * @see com.marklogic.developer.corb.AbstractTask
     * @see com.marklogic.developer.corb.PostBatchUpdateFileTask
     */
    @Usage(description = "Java Class that implements com.marklogic.developer.corb.Task "
            + "or extends com.marklogic.developer.corb.AbstractTask. If POST-BATCH-MODULE "
            + "is also specified, the implementation is expected to invoke the XQuery "
            + "and process the result if any. It can also be specified without POST-BATCH-MODULE "
            + "and an example of this is to add static content to the bottom of the report."
            + "com.marklogic.developer.corb.PostBatchUpdateFileTask (included) "
            + "- Writes the data returned by the POST-BATCH-MODULE to EXPORT-FILE-NAME. "
            + "Also, if EXPORT-FILE-BOTTOM-CONTENT is specified, this task will write "
            + "this value to the EXPORT-FILE-NAME. If EXPORT-FILE-NAME is not specified, "
            + "CoRB uses URIS_BATCH_REF returned by URIS-MODULE as the file name.")
    public static final String POST_BATCH_TASK = "POST-BATCH-TASK";

    /**
     * @deprecated Use the {@link #POST_BATCH_MODULE} option instead.
     * @see #POST_BATCH_MODULE
     */
    @Usage
    public static final String POST_BATCH_XQUERY_MODULE = "POST-BATCH-XQUERY-MODULE";

    /**
     * An XQuery or JavaScript module which, if specified, will be run before
     * batch processing starts.
     * <p>
     * XQuery and JavaScript modules need to have "{@code .xqy}" and
     * "{@code .sjs}" extensions respectively.
     */
    @Usage(description = "An XQuery or JavaScript module which, if specified, will "
            + "be run before batch processing starts. XQuery and JavaScript modules "
            + "need to have .xqy and .sjs extensions respectively.")
    public static final String PRE_BATCH_MODULE = "PRE-BATCH-MODULE";

    /**
     * Java Class that implements {@link com.marklogic.developer.corb.Task} or
     * extends {@link com.marklogic.developer.corb.AbstractTask}.
     * <p>
     * If {@value #PRE_BATCH_MODULE} is also specified, the implementation is
     * expected to invoke the XQuery and process the result if any. It can also
     * be specified without {@value #PRE_BATCH_MODULE} and an example of this is
     * to add a static header to a report.
     * <ul>
     * <li>{@link com.marklogic.developer.corb.PreBatchUpdateFileTask}
     * (included) - Writes the data returned by the {@value #PRE_BATCH_MODULE}
     * to {@value #EXPORT_FILE_NAME}, which can particularly be used to to write
     * dynamic headers for CSV output. Also, if
     * {@value #EXPORT_FILE_TOP_CONTENT} is specified, this task will write this
     * value to the {@value #EXPORT_FILE_NAME} - this option is especially
     * useful for writing fixed headers to reports. If
     * {@value #EXPORT_FILE_NAME} is not specified, CoRB uses
     * {@value #URIS_BATCH_REF} returned by {@value #URIS_MODULE} as the file
     * name.
     * </li>
     * </ul>
     *
     * @see com.marklogic.developer.corb.Task
     * @see com.marklogic.developer.corb.AbstractTask
     * @see com.marklogic.developer.corb.PreBatchUpdateFileTask
     */
    @Usage(description = "Java Class that implements com.marklogic.developer.corb.Task "
            + "or extends com.marklogic.developer.corb.AbstractTask. If PRE-BATCH-MODULE "
            + "is also specified, the implementation is expected to invoke the XQuery "
            + "and process the result if any. It can also be specified without PRE-BATCH-MODULE "
            + "and an example of this is to add a static header to a report."
            + "com.marklogic.developer.corb.PreBatchUpdateFileTask included "
            + "- Writes the data returned by the PRE-BATCH-MODULE to EXPORT-FILE-NAME, "
            + "which can particularly be used to to write dynamic headers for CSV output. "
            + "Also, if EXPORT-FILE-TOP-CONTENT is specified, this task will write this "
            + "value to the EXPORT-FILE-NAME - this option is especially useful for writing "
            + "fixed headers to reports. If EXPORT-FILE-NAME is not specified, CoRB uses "
            + "URIS_BATCH_REF returned by URIS-MODULE as the file name.")
    public static final String PRE_BATCH_TASK = "PRE-BATCH-TASK";

    /**
     * @deprecated Use the {@link #PRE_BATCH_MODULE} option instead.
     * @see #PRE_BATCH_MODULE
     */
    @Usage
    public static final String PRE_BATCH_XQUERY_MODULE = "PRE-BATCH-XQUERY-MODULE";

    /**
     * (Optional)
     * <ul>
     * <li>Default algorithm for PrivateKeyDecrypter is RSA.</li>
     * <li>Default algorithm for JasyptDecrypter is PBEWithMD5AndTripleDES</li>
     * </ul>
     *
     * @see #PRIVATE_KEY_FILE
     * @see #DECRYPTER
     * @see #JASYPT_PROPERTIES_FILE
     */
    @Usage(description = "(Optional)"
            + "Default algorithm for PrivateKeyDecrypter is RSA."
            + "Default algorithm for JasyptDecrypter is PBEWithMD5AndTripleDES")
    public static final String PRIVATE_KEY_ALGORITHM = "PRIVATE-KEY-ALGORITHM";

    /**
     * Required property for
     * {@link com.marklogic.developer.corb.PrivateKeyDecrypter}. This file
     * should be accessible in the classpath or on the file system
     *
     * @see #DECRYPTER
     * @see #PRIVATE_KEY_ALGORITHM
     * @see #JASYPT_PROPERTIES_FILE
     */
    @Usage(description = "Required property for PrivateKeyDecrypter. This file should "
            + "be accessible in the classpath or on the file system")
    public static final String PRIVATE_KEY_FILE = "PRIVATE-KEY-FILE";

    /**
     * XQuery or JavaScript to be executed in a batch for each URI from the
     * {@value #URIS_MODULE} or {@value #URIS_FILE}.
     * <p>
     * Module is expected to have at least one external or global variable with
     * name URI. XQuery and JavaScript modules need to have "{@code .xqy}" and
     * "{@code .sjs}" extensions respectively. If returning multiple values from
     * a JavaScript module, values must be returned as
     * <a href="https://docs.marklogic.com/js/ValueIterator">ValueIterator</a>.
     */
    @Usage(description = "XQuery or JavaScript to be executed in a batch for each URI "
            + "from the URIS-MODULE or URIS-FILE. Module is expected to have at least "
            + "one external or global variable with name URI. XQuery and JavaScript "
            + "modules need to have .xqy and .sjs extensions respectively. If returning "
            + "multiple values from a JavaScript module, values must be returned as ValueIterator.")
    public static final String PROCESS_MODULE = "PROCESS-MODULE";

    /**
     * Java Class that implements {@link com.marklogic.developer.corb.Task} or
     * extends {@link com.marklogic.developer.corb.AbstractTask}.
     * <p>
     * Typically, it can talk to {@value #PROCESS_MODULE} and the do additional
     * processing locally such save a returned value.
     * <ul>
     * <li>{@link com.marklogic.developer.corb.ExportBatchToFileTask} Generates
     * a single file, typically used for reports. Writes the data returned by
     * the {@value #PROCESS_MODULE} to a single file specified by
     * {@value #EXPORT_FILE_NAME}. All returned values from entire CoRB will be
     * streamed into the single file. If {@value #EXPORT_FILE_NAME} is not
     * specified, CoRB uses {@value #URIS_BATCH_REF} returned by
     * {@value #URIS_MODULE} as the file name.</li>
     * <li>{@link com.marklogic.developer.corb.ExportToFileTask} Generates
     * multiple files. Saves the documents returned by each invocation of
     * {@value #PROCESS_MODULE} to a separate local file within
     * {@value #EXPORT_FILE_DIR} where the file name for each document will be
     * the based on the URI.</li>
     * </ul>
     *
     * @see com.marklogic.developer.corb.Task
     * @see com.marklogic.developer.corb.AbstractTask
     */
    @Usage(description = "Java Class that implements com.marklogic.developer.corb.Task "
            + "or extends com.marklogic.developer.corb.AbstractTask. Typically, it "
            + "can talk to XQUERY-MODULE and the do additional processing locally such save a returned value."
            + "com.marklogic.developer.corb.ExportBatchToFileTask Generates a single file, "
            + "typically used for reports. Writes the data returned by the PROCESS-MODULE "
            + "to a single file specified by EXPORT-FILE-NAME. All returned values from "
            + "entire CoRB will be streamed into the single file. If EXPORT-FILE-NAME is not "
            + "specified, CoRB uses URIS_BATCH_REF returned by URIS-MODULE as the file name."
            + "com.marklogic.developer.corb.ExportToFileTask Generates multiple files. "
            + "Saves the documents returned by each invocation of PROCESS-MODULE to a "
            + "separate local file within EXPORT-FILE-DIR where the file name for each "
            + "document will be the based on the URI.")
    public static final String PROCESS_TASK = "PROCESS-TASK";

    /**
     * A comma separated list of MarkLogic error codes for which a
     * QueryException should be retried.
     *
     * @since 2.3.1
     */
    @Usage(description = "A comma separated list of MarkLogic error codes for which a QueryException should be retried.")
    public static final String QUERY_RETRY_ERROR_CODES = "QUERY-RETRY-ERROR-CODES";

    /**
     * A comma separated list of values that if contained in an exception
     * message a QueryException should be retried.
     *
     * @since 2.3.1
     */
    @Usage(description = "A comma separated list of values that if contained in an exception message a QueryException should be retried.")
    public static final String QUERY_RETRY_ERROR_MESSAGE = "QUERY-RETRY-ERROR-MESSAGE";

    /**
     * Time interval, in seconds, between re-query attempts. Default is 20.
     */
    @Usage(description = "Time interval, in seconds, between re-query attempts. "
            + "Default is 20.")
    public static final String QUERY_RETRY_INTERVAL = "QUERY-RETRY-INTERVAL";

    /**
     * Number of re-query attempts before giving up. Default is 2.
     */
    @Usage(description = "Number of re-query attempts before giving up. "
            + "Default is 2.")
    public static final String QUERY_RETRY_LIMIT = "QUERY-RETRY-LIMIT";

    /**
     * A comma separated list of acceptable cipher suites used.
     */
    @Usage(description = "A comma separated list of acceptable cipher suites used.")
    public static final String SSL_CIPHER_SUITES = "SSL-CIPHER-SUITES";

    /**
     * A java class that must implement
     * {@link com.marklogic.developer.corb.SSLConfig}. If not specified, CoRB
     * defaults to @{link com.marklogic.developer.corb.TrustAnyoneSSLConfig} for
     * xccs connections.
     *
     * @see com.marklogic.developer.corb.SSLConfig
     */
    @Usage(description = "A java class that must implement com.marklogic.developer.corb.SSLConfig. "
            + "If not specified, CoRB defaults to com.marklogic.developer.corb.TrustAnyoneSSLConfig for xccs connections.")
    public static final String SSL_CONFIG_CLASS = "SSL-CONFIG-CLASS";

    /**
     * (Optional) A comma separated list of acceptable SSL protocols
     */
    @Usage(description = "(Optional) A comma separated list of acceptable SSL protocols")
    public static final String SSL_ENABLED_PROTOCOLS = "SSL-ENABLED-PROTOCOLS";

    /**
     * Location of the keystore certificate.
     */
    @Usage(description = "Location of the keystore certificate.")
    public static final String SSL_KEYSTORE = "SSL-KEYSTORE";

    /**
     * (Encryptable) Password of the private key.
     */
    @Usage(description = "(Encryptable) Password of the private key.")
    public static final String SSL_KEY_PASSWORD = "SSL-KEY-PASSWORD";

    /**
     * (Encrytable) Password of the keystore file.
     */
    @Usage(description = "(Encrytable) Password of the keystore file.")
    public static final String SSL_KEYSTORE_PASSWORD = "SSL-KEYSTORE-PASSWORD";

    /**
     * Type of the keystore such as 'JKS' or 'PKCS12'.
     */
    @Usage(description = "Type of the keystore such as 'JKS' or 'PKCS12'.")
    public static final String SSL_KEYSTORE_TYPE = "SSL-KEYSTORE-TYPE";

    /**
     * (Optional) A properties file that can be used to load a common SSL
     * configuration.
     */
    @Usage(description = "(Optional) A properties file that can be used to load a common SSL configuration.")
    public static final String SSL_PROPERTIES_FILE = "SSL-PROPERTIES-FILE";

    /**
     * The number of worker threads. Default is 1.
     */
    @Usage(description = "The number of worker threads. Default is 1.")
    public static final String THREAD_COUNT = "THREAD-COUNT";

    /**
     * <a href="https://github.com/marklogic/corb2#uris_batch_ref">URIS_BATCH_REF</a>
     */
    @Usage
    public static final String URIS_BATCH_REF = "URIS_BATCH_REF";

    /**
     * If defined instead of {@value #URIS_MODULE}, URIs will be loaded from the
     * file located on the client. There should only be one URI per line. This
     * path may be relative or absolute.
     * <p>
     * For example, a file containing a list of document identifiers can be used
     * as a {@value #URIS_FILE} and the {@value #PROCESS_MODULE} can query for
     * the document based on this document identifier.
     */
    @Usage(description = "If defined instead of URIS-MODULE, URIs will be loaded "
            + "from the file located on the client. There should only be one URI per line. "
            + "This path may be relative or absolute. For example, a file containing "
            + "a list of document identifiers can be used as a URIS-FILE and the PROCESS-MODULE "
            + "can query for the document based on this document identifier.")
    public static final String URIS_FILE = "URIS-FILE";

    /**
     * Java class that implements
     * {@link com.marklogic.developer.corb.UrisLoader}. A custom class to load
     * URIs instead of built-in loaders for {@value #URIS_MODULE} or
     * {@value #URIS_FILE} options.
     * <p>
     * Example: {@link com.marklogic.developer.corb.FileUrisXMLLoader}
     *
     * @see com.marklogic.developer.corb.AbstractUrisLoader
     * @see com.marklogic.developer.corb.FileUrisLoader
     * @see com.marklogic.developer.corb.FileUrisXMLLoader
     * @see com.marklogic.developer.corb.UrisLoader
     *
     */
    @Usage(description = "Java class that implements com.marklogic.developer.corb.UrisLoader. "
            + "A custom class to load URIs instead of built-in loaders for URIS-MODULE "
            + "or URIS-FILE options. Example: com.marklogic.developer.corb.FileUrisXMLLoader")
    public static final String URIS_LOADER = "URIS-LOADER";

    /**
     * URI selector module written in XQuery or JavaScript. Expected to return a
     * sequence containing the URIs count followed by all the URIs. Optionally,
     * it can also return an arbitrary string as a first item in this sequence -
     * refer to
     * <a href="https://github.com/marklogic/corb2#uris_batch_ref">URIS_BATCH_REF</a>
     * section.
     * <p>
     * XQuery and JavaScript modules need to have "{@code .xqy}" and
     * "{@code .sjs}" extensions respectively. JavaScript modules must return a
     * <a href="https://docs.marklogic.com/js/ValueIterator">ValueIterator</a>.
     */
    @Usage(description = "URI selector module written in XQuery or JavaScript. "
            + "Expected to return a sequence containing the URIs count followed by all the URIs. "
            + "Optionally, it can also return an arbitrary string as a first item in this sequence - "
            + "refer to URIS_BATCH_REF section below. XQuery and JavaScript modules "
            + "need to have .xqy and .sjs extensions respectively. "
            + "JavaScript modules must return a ValueIterator.")
    public static final String URIS_MODULE = "URIS-MODULE";

    /**
     * One or more replace patterns for URIs - Used by java to truncate the
     * length of URIs on the client side, typically to reduce java heap size in
     * very large batch jobs, as the CoRB java client holds all the URIS in
     * memory while processing is in progress.
     * <p>
     * If truncated, {@value #PROCESS_MODULE} needs to reconstruct the URI
     * before trying to use {@code fn:doc()} to fetch the document.
     * <p>
     * Usage:
     * {@code URIS_REPLACE_PATTERN=pattern1,replace1,pattern2,replace2,...)}
     * <p>
     * <b>Example:</b>
     * {@code URIS-REPLACE-PATTERN=/com/marklogic/sample/,,.xml}, - Replaces
     * "{@code /com/marklogic/sample/}" and "{@code .xml}" with empty strings.
     * So, the CoRB client only needs to cache the id "{@code 1234}" instead of
     * the entire URI "{@code /com/marklogic/sample/1234.xml}".
     * <P>
     * In the transform {@value #PROCESS_MODULE}, we need to do
     * {@code let $URI := fn:concat("/com/marklogic/sample/",$URI,".xml")}.
     */
    @Usage(description = "One or more replace patterns for URIs - Used by java "
            + "to truncate the length of URIs on the client side, typically to "
            + "reduce java heap size in very large batch jobs, as the CoRB java "
            + "client holds all the URIS in memory while processing is in progress. "
            + "If truncated, PROCESS-MODULE needs to reconstruct the URI before "
            + "trying to duse fn:doc() to fetch the document. "
            + "Usage: URIS-REPLACE-PATTERN=pattern1,replace1,pattern2,replace2,...)"
            + "Example:"
            + "URIS-REPLACE-PATTERN=/com/marklogic/sample/,,.xml, - Replace /com/marklogic/sample/ "
            + "and .xml with empty strings. So, CoRB client only needs to cache the id '1234' i"
            + "nstead of the entire URI /com/marklogic/sample/1234.xml. In the transform "
            + "PROCESS-MODULE, we need to do let $URI := fn:concat(\"/com/marklogic/sample/\",$URI,\".xml\")")
    public static final String URIS_REPLACE_PATTERN = "URIS-REPLACE-PATTERN";

    /**
     * Number attempts to connect to ML before giving up. Default is 3
     */
    @Usage(description = "Number attempts to connect to ML before giving up. "
            + "Default is 3")
    public static final String XCC_CONNECTION_RETRY_LIMIT = "XCC-CONNECTION-RETRY-LIMIT";

    /**
     * Time interval, in seconds, between retry attempts. Default is 60.
     */
    @Usage(description = "Time interval, in seconds, between retry attempts. "
            + "Default is 60.")
    public static final String XCC_CONNECTION_RETRY_INTERVAL = "XCC-CONNECTION-RETRY-INTERVAL";

    /**
     * Connection string to MarkLogic XDBC Server.
     */
    @Usage(description = "Connection string to MarkLogic XDBC Server.")
    public static final String XCC_CONNECTION_URI = "XCC-CONNECTION-URI";

    /**
     * (Optional)
     */
    @Usage(description = "(Optional)")
    public static final String XCC_DBNAME = "XCC-DBNAME";

    /**
     * Required if {@value #XCC_CONNECTION_URI} is not specified.
     */
    @Usage(description = "Required if XCC-CONNECTION-URI is not specified.")
    public static final String XCC_HOSTNAME = "XCC-HOSTNAME";

    /**
     * Required if {@value #XCC_CONNECTION_URI} is not specified.
     */
    @Usage(description = "Required if XCC-CONNECTION-URI is not specified.")
    public static final String XCC_PASSWORD = "XCC-PASSWORD";

    /**
     * Required if {@value #XCC_CONNECTION_URI} is not specified.
     */
    @Usage(description = "Required if XCC-CONNECTION-URI is not specified.")
    public static final String XCC_PORT = "XCC-PORT";

    /**
     * Required if {@value #XCC_CONNECTION_URI} is not specified.
     */
    @Usage(description = "Required if XCC-CONNECTION-URI is not specified.")
    public static final String XCC_USERNAME = "XCC-USERNAME";

    /**
     * In order to use this option a class
     * {@link com.marklogic.developer.corb.FileUrisXMLLoader} has to be
     * specified in the {@value #URIS_LOADER} option. If defined instead of
     * {@value #URIS_MODULE}, XML nodes will be used as URIs from the file
     * located on the client. The file path may be relative or absolute.
     * <p>
     * Default processing will select all of the child elements of the document
     * element (i.e. {@code \/*\/*)}.
     * <p>
     * The {@value #XML_NODE} option can be specified with an XPath to address a
     * different set of nodes.
     *
     * @see #XML_NODE
     * @since 2.3.1
     */
    @Usage(description = "n order to use this option a class com.marklogic.developer.corb.FileUrisXMLLoader "
            + "has to be specified in the URIS-LOADER option. If defined instead of "
            + "URIS-MODULE, XML nodes will be used as URIs from the file located on the client. "
            + "The file path may be relative or absolute. Default processing will "
            + "select all of the child elements of the document element (i.e. /*/*). "
            + "The XML-NODE option can be specified with an XPath to address a different set of nodes.")
    public static final String XML_FILE = "XML-FILE";

    /**
     * An XPath to address the nodes to be returned in an {@value #XML_FILE} by
     * the {@link com.marklogic.developer.corb.FileUrisXMLLoader}.
     * <p>
     * For example, a file containing a list of nodes wrapped by a parent
     * element can be used as a {@value #XML_FILE} and the
     * {@value #PROCESS_MODULE} can unquote the URI string as node to do further
     * processing with the node.
     * <p>
     * If not specified, the default behavior is to select the child elements of
     * the document element (i.e. {@code \/*\/*)}
     *
     * @see #XML_FILE
     * @since 2.3.1
     */
    @Usage(description = "An XPath to address the nodes to be returned in an XML-FILE "
            + "by the com.marklogic.developer.corb.FileUrisXMLLoader. For example, "
            + "a file containing a list of nodes wrapped by a parent element can "
            + "be used as a XML-FILE and the XQUERY-MODULE can unquote the URI "
            + "string as node to do further processing with the node. If not specified, "
            + "the default behavior is to select the child elements of the document element (i.e. /*/*)")
    public static final String XML_NODE = "XML-NODE";

    /**
     *
     * @deprecated Use the {@link #PROCESS_MODULE} option instead.
     * @see #PROCESS_MODULE
     */
    @Usage(description = "Use PROCESS_MODULE instead")
    public static final String XQUERY_MODULE = "XQUERY-MODULE";

    private Options() {
    }

}

/**
 * Annotation used to document attributes of the CoRB Options.
 *
 * @author Mads Hansen, MarkLogic Corporation
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Usage {

    String description() default "";

}
