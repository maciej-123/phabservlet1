//We didn't full understand how to implement unit tests but here is proof that we tried to.
//import org.junit.Assert;
//        import org.junit.Before;
//        import org.junit.Test;
//        import org.mockito.Mock;
//        import org.mockito.MockitoAnnotations;
//
//        import javax.servlet.ServletException;
//        import javax.servlet.http.HttpServletRequest;
//        import javax.servlet.http.HttpServletResponse;
//
//        import java.io.FileInputStream;
//        import java.io.IOException;
//        import java.io.PrintWriter;
//        import java.io.StringWriter;
//        import java.util.logging.LogManager;
//        import java.util.logging.Logger;
//
//        import static org.mockito.Mockito.*;
//
//        import static org.hamcrest.MatcherAssert.assertThat;
//        import static org.hamcrest.Matchers.is;
//        import static org.hamcrest.Matchers.equalTo;
//
//public class test_doGet {
//    @Mock
//    HttpServletRequest request;
//    @Mock
//    HttpServletResponse response;
//
//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testDoGet_replenishstock() throws IOException, ServletException {
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(printWriter);
//        when(request.getServletPath()).thenReturn("/replenishStock");
//        when(request.getRequestURI()).thenReturn("/replenishStock");
//
//        phabservlet1 Servlet = new phabservlet1();
//        Servlet.doGet(request, response);
//        String output = stringWriter.getBuffer().toString();
//
//        Assert.assertThat(output, is(equalTo(
//                "The url cannot be null\n" +
//                        "Setting Stock to Max\n" +
//                        "Deleting Test Rows Paddington\n" +
//                        "Cannot invoke \"java.sql.Connection.createStatement()\" because \"this.c\" is null" +
//                        "Filling In PHAB Paddington Database\n" +
//                        "Cannot invoke \"java.sql.Connection.createStatement()\" because \"this.c\" is null" +
//                        "Deleting Test Rows Paddington\n" +
//                        "Cannot invoke \"java.sql.Connection.createStatement()\" because \"this.c\" is null" +
//                        "Filling In PHAB Paddington Database\n" +
//                        "Cannot invoke \"java.sql.Connection.createStatement()\" because \"this.c\" is null" +
//                        "Deleting Test Rows Paddington\nCannot invoke \"java.sql.Connection.createStatement()\" because \"this.c\" is null" +
//                        "Filling In PHAB Paddington Database\n" +
//                        "Cannot invoke \"java.sql.Connection.createStatement()\" because \"this.c\" is null"
//        )));
//    }
//
//    @Test
//    public void testDoGet_text_database() throws IOException, ServletException {
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(printWriter);
//        when(request.getServletPath()).thenReturn("/text_database");
//
//        phabservlet1 Servlet = new phabservlet1();
//        Servlet.doGet(request, response);
//        String output = stringWriter.getBuffer().toString();
//
//        Assert.assertThat(output, is(equalTo(
//                "The url cannot be null" +
//                        "<b>This is a text file form database with parameters as follows</b>\n<b>Manufacturer|Name|Quantity|SalesPrice|Purchase_Price|Full_Stock|Limit_to_1|CurrentStock</b>\n<b>###Paddington Branch###</b>\nCold and Flu,,,Sale price,Purchase price,Full stock,Limited to 1 pack per purchase,Current Stock\n#Vicks,Vaporub,100g,4.5,3.7,15,0,@15\n#Vicks,First Defence,15ml,6.8,5,20,0,@20\n#Gsk,Night Nurse,160ml,8.5,7,30,0,@30\n#Gsk,Night Nurse,160ml,9,7.5,30,0,@30\n#Lemsip,Max,16 caps,4.2,3.7,25,0,@25\n#Lemsip,Standard,10 sachets,4.5,3.5,25,0,@25\n#Sudafed,Day and Night,16 caps,4.5,3.2,30,1,@30\n#Sudafed,Max,16 caps,4.2,3.2,30,1,@30\n#Benylin,Mucus relief,16 caps,4.8,3.2,20,0,@20\n#Benylin,4 flu,24 caps,6,4.9,20,0,@20\n,,,,,,,\n#Skincare,,,,,,,\n#E45,Psoriasis cream,50ml,20,16,15,0,@15\n#Eurax,Skin cream,100g,5.7,4.2,15,0,@15\n#Eucerin,Skin relief cream,50ml,9,7,20,0,@20\n#Eucerin,Face scrub,100ml,7.5,6,20,0,@20\n#Dermalex,Psoriasis cream,150ml,30,25,10,0,@10\n#Dermalex,Repair and Restore,100g,12,10,10,0,@10\n#Dermalex,Eczema cream,30g,12,9.7,20,0,@20\n#Dermalex,Eczema cream,100g,25,22.2,5,0,@5\n#Cetaphil,Moisturising cream,50ml,10,7.6,20,0,@20\n#Cetaphil,Exfoliating cleanser,180ml,12,10.1,20,0,@20\n,,,,,,,\n#Headaches and pain relief,,,,,,,\n#Nurofen,Meltlets,16 caps,4,3.7,40,0,@40\n#Nurofen,Express,16 caps,4,3.5,30,0,@30\n#Nurofen,Max strength,32 caps,7,6.2,25,0,@25\n#Nurofen,Standard,16 caps,4,3.2,30,0,@30\n#Cuprofen ,Max strength,96 caps,11,9,20,1,@20\n#Solpadeine,Headache,16 caps,2,1.6,20,1,@20\n#Anadin,Extra,16 caps,2.3,2,30,1,@30\n#Anadin,Triple action,12 caps,2,1.9,30,1,@30\n#Anadin,Original,16 caps,1.8,1.5,30,1,@30\n#Disprin,Soluble,32 tablets,3.6,2.8,20,1,@20\n,,,,,,,\n#Digestion,,,,,,,\n#Dioralyte,Blackcurrant,12 sachets,8,7.3,20,0,@20\n#Dioralyte,Lemon,12 sachets,8,7.3,20,0,@20\n#Gaviscon,Chewable,24 tablets,4.2,3.5,25,0,@25\n#Senokot,Max,10 tablets,3,2.7,10,0,@10\n#Gaviscon,Advance,300ml,10,8.1,10,0,@10\n,,,,,,,\n#Allergy,,,,,,,\n#Benadryl,Relief,24 caps,9,7.1,20,0,@20\n#Piriteze,tabs,7 tablets,3,2.3,20,0,@20\n#Beconase,Relief,100 sprays,6,4,20,0,@20\n,,,,,,,\n#First aid,,,,,,,\n#Dettol,Antiseptic,500ml,3.2,3,20,0,@20\n#Dettol,Hand sanitizer,500ml,7,6.3,50,0,@50\n#Elastoplast,plasters,20 plasters,3,2,30,0,@30\n#TCP,Liquid,200ml,4,3.2,20,0,@20"
//        )));
//    }
//
//    @Test
//    public void testDoGet_getLimitOne() throws IOException, ServletException {
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//
//        when(response.getWriter()).thenReturn(printWriter);
//        when(request.getServletPath()).thenReturn("/getLimitOne");
//
//        phabservlet1 Servlet = new phabservlet1();
//        Servlet.inputGlobalVars("vicks","vaporub","Paddington");
//        Servlet.doGet(request, response);
//        String output = stringWriter.getBuffer().toString();
//
//        Assert.assertThat(output, is(equalTo(
//                "The url cannot be null"
//        )));
//    }
//
//
//}