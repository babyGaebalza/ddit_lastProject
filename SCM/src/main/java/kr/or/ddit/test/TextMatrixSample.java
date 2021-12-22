//package kr.or.ddit.test;
//
//	import java.io.IOException;
//	import org.apache.pdfbox.pdmodel.PDDocument;
//	import org.apache.pdfbox.pdmodel.PDPage;
//	import org.apache.pdfbox.pdmodel.common.PDRectangle;
//	import org.apache.pdfbox.pdmodel.PDPageContentStream;
//	import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
//     import org.apache.pdfbox.pdmodel.font.PDFont;
//	import org.apache.pdfbox.pdmodel.font.PDType1Font;
//	import org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName;
//	import org.apache.pdfbox.util.Matrix;
//	
//
//
//public class TextMatrixSample {
//
//
//
//
//
//1	/*
//2	 * Licensed to the Apache Software Foundation (ASF) under one or more
//3	 * contributor license agreements.  See the NOTICE file distributed with
//4	 * this work for additional information regarding copyright ownership.
//5	 * The ASF licenses this file to You under the Apache License, Version 2.0
//6	 * (the "License"); you may not use this file except in compliance with
//7	 * the License.  You may obtain a copy of the License at
//8	 *
//9	 *      http://www.apache.org/licenses/LICENSE-2.0
//10	 *
//11	 * Unless required by applicable law or agreed to in writing, software
//12	 * distributed under the License is distributed on an "AS IS" BASIS,
//13	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//14	 * See the License for the specific language governing permissions and
//15	 * limitations under the License.
//16	 */
//17
//36	    /**
//37	     * Constructor.
//38	     */
//39	    public TextMatrixSample()
//40	    {
//41	    }
//42	
//43	    /**
//44	     * creates a sample document with some text using a text matrix.
//45	     *
//46	     * @param message The message to write in the file.
//47	     * @param outfile The resulting PDF.
//48	     *
//49	     * @throws IOException If there is an error writing the data.
//50	     */
//51	    public void doIt( String message, String  outfile ) throws IOException
//52	    {
//53	        // the document
//54	        try (PDDocument doc = new PDDocument())
//55	        {
//56	            // Page 1
//57	            PDFont font = new PDType1Font(FontName.HELVETICA);
//58	            PDPage page = new PDPage(PDRectangle.A4);
//59	            doc.addPage(page);
//60	            float fontSize = 12.0f;
//61	
//62	            PDRectangle pageSize = page.getMediaBox();
//63	            float centeredXPosition = (pageSize.getWidth() - fontSize/1000f)/2f;
//64	            float stringWidth = font.getStringWidth( message );
//65	            float centeredYPosition = (pageSize.getHeight() - (stringWidth*fontSize)/1000f)/3f;
//66	
//67	            PDPageContentStream contentStream = new PDPageContentStream(doc, page, AppendMode.OVERWRITE, false);
//68	            contentStream.setFont( font, fontSize );
//69	            contentStream.beginText();
//70	            // counterclockwise rotation
//71	            for (int i=0;i<8;i++) 
//72	            {
//73	                contentStream.setTextMatrix(Matrix.getRotateInstance(i * Math.PI * 0.25,
//74	                        centeredXPosition, pageSize.getHeight() - centeredYPosition));
//75	                contentStream.showText(message + " " + i);
//76	            }
//77	            // clockwise rotation
//78	            for (int i=0;i<8;i++) 
//79	            {
//80	                contentStream.setTextMatrix(Matrix.getRotateInstance(-i*Math.PI*0.25,
//81	                        centeredXPosition, centeredYPosition));
//82	                contentStream.showText(message + " " + i);
//83	            }
//84	
//85	            contentStream.endText();
//86	            contentStream.close();
//87	
//88	            // Page 2
//89	            page = new PDPage(PDRectangle.A4);
//90	            doc.addPage(page);
//91	            fontSize = 1.0f;
//92	
//93	            contentStream = new PDPageContentStream(doc, page, AppendMode.OVERWRITE, false);
//94	            contentStream.setFont( font, fontSize );
//95	            contentStream.beginText();
//96	
//97	            // text scaling and translation
//98	            for (int i=0;i<10;i++)
//99	            {
//100	                contentStream.setTextMatrix(new Matrix(12f + (i * 6), 0, 0, 12f + (i * 6), 
//101	                                                       100, 100f + i * 50));
//102	                contentStream.showText(message + " " + i);
//103	            }
//104	            contentStream.endText();
//105	            contentStream.close();
//106	
//107	            // Page 3
//108	            page = new PDPage(PDRectangle.A4);
//109	            doc.addPage(page);
//110	            fontSize = 1.0f;
//111	
//112	            contentStream = new PDPageContentStream(doc, page, AppendMode.OVERWRITE, false);
//113	            contentStream.setFont( font, fontSize );
//114	            contentStream.beginText();
//115	
//116	            int i = 0;
//117	            // text scaling combined with rotation 
//118	            contentStream.setTextMatrix(new Matrix(12, 0, 0, 12, centeredXPosition, centeredYPosition*1.5f));
//119	            contentStream.showText(message + " " + i++);
//120	
//121	            contentStream.setTextMatrix(new Matrix(0, 18, -18, 0, centeredXPosition, centeredYPosition*1.5f));
//122	            contentStream.showText(message + " " + i++);
//123	
//124	            contentStream.setTextMatrix(new Matrix(-24, 0, 0, -24, centeredXPosition, centeredYPosition*1.5f));
//125	            contentStream.showText(message + " " + i++);
//126	
//127	            contentStream.setTextMatrix(new Matrix(0, -30, 30, 0, centeredXPosition, centeredYPosition*1.5f));
//128	            contentStream.showText(message + " " + i++);
//129	
//130	            contentStream.endText();
//131	            contentStream.close();
//132	
//133	            doc.save( outfile );
//134	        }
//135	    }
//136	
//137	    /**
//138	     * This will create a PDF document with some examples how to use a text matrix.
//139	     * 
//140	     * @param args Command line arguments.
//141	     */
//142	    public static void main(String[] args) throws IOException
//143	    {
//144	        UsingTextMatrix app = new UsingTextMatrix();
//145	        if( args.length != 2 )
//146	        {
//147	            app.usage();
//148	        }
//149	        else
//150	        {
//151	            app.doIt( args[0], args[1] );
//152	        }
//153	    }
//154	
//155	    /**
//156	     * This will print out a message telling how to use this example.
//157	     */
//158	    private void usage()
//159	    {
//160	        System.err.println( "usage: " + this.getClass().getName() + " <Message> <output-file>" );
//161	    }
//162	}
//}
// 