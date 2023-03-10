/**
 * Copyright (C) 2011-2015 The XDocReport Team <xdocreport@googlegroups.com>
 *
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package fr.opensagres.xdocreport.document.docx.preprocessor;

import java.io.InputStream;
import java.io.StringWriter;

import junit.framework.TestCase;
import fr.opensagres.xdocreport.core.io.IOUtils;
import fr.opensagres.xdocreport.document.docx.preprocessor.sax.DocxPreprocessor;

public class DocxPreprocessorMergefieldWithConditionTestCase
    extends TestCase
{

    public void testWithSimpleFieldAndConditionWithSimpleQuote()
        throws Exception
    {
        DocxPreprocessor preprocessor = new DocxPreprocessor();
        InputStream stream =
                        IOUtils.toInputStream( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                                        + "<w:document "
                                        + "xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" "
                                        + "xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
                                        + "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" "
                                        + "xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" "
                                        + "xmlns:v=\"urn:schemas-microsoft-com:vml\" "
                                        + "xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
                                        + "xmlns:w10=\"urn:schemas-microsoft-com:office:word\" "
                                        + "xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" "
                                        + "xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">"
                                        + "<w:fldSimple   w:instr=\" MERGEFIELD  &quot;[#if name = 'world']world[#else]not world[/#if]&quot;  \\* MERGEFORMAT \">"
                                        + "<w:r w:rsidRPr=\"009C7F29\">" + "<w:rPr>" + "<w:noProof/>"
                                        + "<w:lang w:val=\"en-US\"/>" + "</w:rPr>"
                                        + "<w:t>??[#if name = 'world']world[#else]not worl??+ </w:t>" + "</w:r>"
                                        + "</w:fldSimple>" + "</w:document>", "UTF-8"  );

        StringWriter writer = new StringWriter();
        preprocessor.preprocess( "test", stream, writer, null, null, null );

        assertEquals( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<w:document "
            + "xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" "
            + "xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
            + "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" "
            + "xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" "
            + "xmlns:v=\"urn:schemas-microsoft-com:vml\" "
            + "xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
            + "xmlns:w10=\"urn:schemas-microsoft-com:office:word\" "
            + "xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" "
            + "xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">"
            // +
            // "<w:fldSimple	w:instr=\" MERGEFIELD  &quot;[#if name = 'world']world[#else]not world[/#if]&quot;  \\* MERGEFORMAT \">"
            + "<w:r w:rsidRPr=\"009C7F29\">" + "<w:rPr>" + "<w:noProof/>" + "<w:lang w:val=\"en-US\"/>" + "</w:rPr>"
            // + "<w:t>??[#if name = 'world']world[#else]not worl??+ </w:t>"
            + "<w:t>[#if name = 'world']world[#else]not world[/#if]</w:t>" + "</w:r>"
            // + "</w:fldSimple>"
            + "</w:document>", writer.toString() );

    }

    public void testWithFieldAndConditionWithDoubleQuote()
        throws Exception
    {
        DocxPreprocessor preprocessor = new DocxPreprocessor();
        InputStream stream =
                        IOUtils.toInputStream( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                                        + "<w:document "
                                        + "xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" "
                                        + "xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
                                        + "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" "
                                        + "xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" "
                                        + "xmlns:v=\"urn:schemas-microsoft-com:vml\" "
                                        + "xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
                                        + "xmlns:w10=\"urn:schemas-microsoft-com:office:word\" "
                                        + "xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" "
                                        + "xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">"
                                        + "<w:p w:rsidR=\"009C44CF\" w:rsidRPr=\"00EF7951\" w:rsidRDefault=\"009C7F29\">"
                                        + "<w:pPr>"
                                        + "<w:rPr>"
                                        + "<w:lang w:val=\"en-US\"/>"
                                        + "</w:rPr>"
                                        + "</w:pPr>"
                                        + "<w:r>"
                                        + "<w:fldChar w:fldCharType=\"begin\"/>"
                                        + "</w:r>"
                                        + "<w:r w:rsidRPr=\"009C7F29\">"
                                        + "<w:rPr>"
                                        + "<w:lang w:val=\"en-US\"/>"
                                        + "</w:rPr>"
                                        + "<w:instrText xml:space=\"preserve\"> MERGEFIELD  \"[#if name != \\\"world\\\"]world[#else]not world[/#if]\"  \\* MERGEFORMAT </w:instrText>"
                                        + "</w:r>" + "<w:r>" + "<w:fldChar w:fldCharType=\"separate\"/>" + "</w:r>"
                                        + "<w:r w:rsidRPr=\"009C7F29\">" + "<w:rPr>" + "<w:noProof/>"
                                        + "<w:lang w:val=\"en-US\"/>" + "</w:rPr>"
                                        + "<w:t>[#if name != \"world\"]world[#else]not wor??</w:t>" + "</w:r>" + "<w:r>"
                                        + "<w:fldChar w:fldCharType=\"end\"/>" + "</w:r>" + "</w:p>" + "</w:document>", "UTF-8");

        StringWriter writer = new StringWriter();
        preprocessor.preprocess( "test", stream, writer, null, null, null );

        assertEquals( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<w:document "
            + "xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" "
            + "xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
            + "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" "
            + "xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" "
            + "xmlns:v=\"urn:schemas-microsoft-com:vml\" "
            + "xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
            + "xmlns:w10=\"urn:schemas-microsoft-com:office:word\" "
            + "xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" "
            + "xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">"
            + "<w:p w:rsidR=\"009C44CF\" w:rsidRPr=\"00EF7951\" w:rsidRDefault=\"009C7F29\">" + "<w:pPr>" + "<w:rPr>"
            + "<w:lang w:val=\"en-US\"/>" + "</w:rPr>" + "</w:pPr>"
            // + "<w:r>"
            // + "<w:fldChar w:fldCharType=\"begin\"/>"
            // + "</w:r>"
            // + "<w:r w:rsidRPr=\"009C7F29\">"
            // + "<w:rPr>"
            // + "<w:lang w:val=\"en-US\"/>"
            // + "</w:rPr>"
            // +
            // "<w:instrText xml:space=\"preserve\"> MERGEFIELD  \"[#if name != \\\"world\\\"]world[#else]not world[/#if]\"  \\* MERGEFORMAT </w:instrText>"
            // + "</w:r>"
            // + "<w:r>"
            // + "<w:fldChar w:fldCharType=\"separate\"/>"
            // + "</w:r>"
            + "<w:r w:rsidRPr=\"009C7F29\">" + "<w:rPr>" + "<w:noProof/>" + "<w:lang w:val=\"en-US\"/>" + "</w:rPr>"
            // + "<w:t>[#if name != \"world\"]world[#else]not wor??</w:t>"
            + "<w:t>[#if name != \"world\"]world[#else]not world[/#if]</w:t>" + "</w:r>"
            // + "<w:r>"
            // + "<w:fldChar w:fldCharType=\"end\"/>"
            // + "</w:r>"
            + "</w:p>" + "</w:document>", writer.toString() );

    }

    public void testWithFieldAndConditionWithSimpleQuote()
        throws Exception
    {
        DocxPreprocessor preprocessor = new DocxPreprocessor();

        InputStream stream =
                        IOUtils.toInputStream((
                              "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                                  + "<w:document "
                                  + "xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" "
                                  + "xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
                                  + "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" "
                                  + "xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" "
                                  + "xmlns:v=\"urn:schemas-microsoft-com:vml\" "
                                  + "xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
                                  + "xmlns:w10=\"urn:schemas-microsoft-com:office:word\" "
                                  + "xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" "
                                  + "xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">"
                                  + "<w:p w:rsidR=\"009C44CF\" w:rsidRPr=\"00EF7951\" w:rsidRDefault=\"009C7F29\">"
                                  + "<w:pPr>"
                                  + "<w:rPr>"
                                  + "<w:lang w:val=\"en-US\"/>"
                                  + "</w:rPr>"
                                  + "</w:pPr>"
                                  + "<w:r>"
                                  + "<w:fldChar w:fldCharType=\"begin\"/>"
                                  + "</w:r>"
                                  + "<w:r w:rsidRPr=\"009C7F29\">"
                                  + "<w:rPr>"
                                  + "<w:lang w:val=\"en-US\"/>"
                                  + "</w:rPr>"
                                  + "<w:instrText xml:space=\"preserve\"> MERGEFIELD  \"[#if name != 'world']world[#else]not world[/#if]\"  \\* MERGEFORMAT </w:instrText>"
                                  + "</w:r>" + "<w:r>" + "<w:fldChar w:fldCharType=\"separate\"/>" + "</w:r>"
                                  + "<w:r w:rsidRPr=\"009C7F29\">" + "<w:rPr>" + "<w:noProof/>"
                                  + "<w:lang w:val=\"en-US\"/>" + "</w:rPr>"
                                  + "<w:t>[#if name != 'world']world[#else]not wor??</w:t>" + "</w:r>" + "<w:r>"
                                  + "<w:fldChar w:fldCharType=\"end\"/>" + "</w:r>" + "</w:p>" + "</w:document>" ), "UTF-8");

        StringWriter writer = new StringWriter();
        preprocessor.preprocess( "test", stream, writer, null, null, null );

        assertEquals( "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<w:document "
            + "xmlns:ve=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" "
            + "xmlns:o=\"urn:schemas-microsoft-com:office:office\" "
            + "xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" "
            + "xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" "
            + "xmlns:v=\"urn:schemas-microsoft-com:vml\" "
            + "xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" "
            + "xmlns:w10=\"urn:schemas-microsoft-com:office:word\" "
            + "xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" "
            + "xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\">"
            + "<w:p w:rsidR=\"009C44CF\" w:rsidRPr=\"00EF7951\" w:rsidRDefault=\"009C7F29\">" + "<w:pPr>" + "<w:rPr>"
            + "<w:lang w:val=\"en-US\"/>" + "</w:rPr>" + "</w:pPr>"
            // + "<w:r>"
            // + "<w:fldChar w:fldCharType=\"begin\"/>"
            // + "</w:r>"
            // + "<w:r w:rsidRPr=\"009C7F29\">"
            // + "<w:rPr>"
            // + "<w:lang w:val=\"en-US\"/>"
            // + "</w:rPr>"
            // +
            // "<w:instrText xml:space=\"preserve\"> MERGEFIELD  \"[#if name != 'world']world[#else]not world[/#if]\"  \\* MERGEFORMAT </w:instrText>"
            // + "</w:r>"
            // + "<w:r>"
            // + "<w:fldChar w:fldCharType=\"separate\"/>"
            // + "</w:r>"
            + "<w:r w:rsidRPr=\"009C7F29\">" + "<w:rPr>" + "<w:noProof/>" + "<w:lang w:val=\"en-US\"/>" + "</w:rPr>"
            // + "<w:t>[#if name != 'world']world[#else]not wor??</w:t>"
            + "<w:t>[#if name != 'world']world[#else]not world[/#if]</w:t>" + "</w:r>"
            // + "<w:r>"
            // + "<w:fldChar w:fldCharType=\"end\"/>"
            // + "</w:r>"
            + "</w:p>" + "</w:document>", writer.toString() );

    }

}
