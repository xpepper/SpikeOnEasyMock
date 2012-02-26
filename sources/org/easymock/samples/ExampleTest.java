package org.easymock.samples;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExampleTest {

    private ClassUnderTest classUnderTest;

    private Collaborator mock;

    @Before
    public void setup() {
        mock = createMock(Collaborator.class);
        classUnderTest = new ClassUnderTest();
        classUnderTest.addListener(mock);
    }

    @Test
    public void removeNonExistingDocument() {
        replay(mock);
        boolean wasRemoved = classUnderTest.removeDocument("Does not exist");
        assertTrue(wasRemoved);
    }

    @Test
    public void addDocument() {
        mock.documentAdded("New Document");
        replay(mock);
        
        classUnderTest.addDocument("New Document", new byte[0]);
        
        verify(mock);
    }


    @Test
    public void addAndChangeDocument() {
        mock.documentAdded("Document");
        mock.documentChanged("Document");
        expectLastCall().times(3);
        replay(mock);
        
        classUnderTest.addDocument("Document", new byte[0]);
        classUnderTest.addDocument("Document", new byte[0]);
        classUnderTest.addDocument("Document", new byte[0]);
        classUnderTest.addDocument("Document", new byte[0]);
        
        verify(mock);
    }

    @Test
    public void voteForRemoval() {
        mock.documentAdded("Document");
        expect(mock.voteForRemoval("Document")).andReturn((byte) 42);
        mock.documentRemoved("Document");
        replay(mock);
        
        classUnderTest.addDocument("Document", new byte[0]);
        boolean wasRemoved = classUnderTest.removeDocument("Document");
        
        assertTrue(wasRemoved);
        verify(mock);
    }

    @Test
    public void voteAgainstRemoval() {
        mock.documentAdded("Document");
        expect(mock.voteForRemoval("Document")).andReturn((byte)-1);
        replay(mock);
        
        classUnderTest.addDocument("Document", new byte[0]);
        boolean wasRemoved = classUnderTest.removeDocument("Document");

        assertFalse(wasRemoved);
        verify(mock);
    }

    @Test
    public void voteForRemovals() {
        String firstDocumentTitle = "Document 1";
        String secondDocumentTitle = "Document 2";
        String[] documents = new String[] { firstDocumentTitle, secondDocumentTitle };
        
        mock.documentAdded(firstDocumentTitle);
        mock.documentAdded(secondDocumentTitle);
        expect(mock.voteForRemovals(aryEq(documents))).andReturn((byte) 42);
        mock.documentRemoved(firstDocumentTitle);
        mock.documentRemoved(secondDocumentTitle);
        replay(mock);

        classUnderTest.addDocument(firstDocumentTitle, new byte[0]);
        classUnderTest.addDocument(secondDocumentTitle, new byte[0]);
        boolean wereRemoved = classUnderTest.removeDocuments(documents);
        
        assertTrue(wereRemoved);
        verify(mock);
    }

    @Test
    public void voteAgainstRemovals() {
        String firstDocumentTitle = "Document 1";
        String secondDocumentTitle = "Document 2";
        mock.documentAdded(firstDocumentTitle);
        mock.documentAdded(secondDocumentTitle);
        String[] documents = new String[] { firstDocumentTitle, secondDocumentTitle };
        expect(mock.voteForRemovals(aryEq(documents))).andReturn((byte) -42);
        replay(mock);

        classUnderTest.addDocument(firstDocumentTitle, new byte[0]);
        classUnderTest.addDocument(secondDocumentTitle, new byte[0]);
        boolean removed = classUnderTest.removeDocuments(new String[] { firstDocumentTitle, secondDocumentTitle });
        
        assertFalse(removed);
        verify(mock);
    }
 
}
