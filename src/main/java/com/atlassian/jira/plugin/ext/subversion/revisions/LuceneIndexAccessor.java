package com.atlassian.jira.plugin.ext.subversion.revisions;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;

import java.io.IOException;

/**
 * Small abstraction for Lucene index reader and writer acquisition. Helps contain the dependencies of
 * RevisionIndexer on the internals of lucene and specifically the knock-on effects of static references
 * to Bonnie classes in LuceneUtils. Introduced to aid testability.
 *
 * @since 0.9.12
 */
interface LuceneIndexAccessor {
    /**
     * Gets a Lucene {@link org.apache.lucene.index.IndexReader} at the given path.
     *
     * @param path the path.
     * @return the IndexReader.
     * @throws IOException if there's some problem getting the reader.
     */
    IndexReader getIndexReader(String path) throws IOException;

    /**
     * Gets a Lucene {@link org.apache.lucene.index.IndexWriter} at the given path.
     *
     * @param path     the path.
     * @param create   if true, then create if absent.
     * @param analyzer the {@link org.apache.lucene.analysis.Analyzer} to use.
     * @return the IndexWriter.
     * @throws IOException if there's some problem getting the writer.
     */
    IndexWriter getIndexWriter(String path, boolean create, Analyzer analyzer) throws IOException;
}
