//package alertsender;
//
////import dao.AlertDAO;
////import dao.AlertDAO;
////import dao.AlertDAO;
////import integration.cta.traintracker.client.CustomerAlertsClient;
////import model.entity.cta.customeralerts.CTAAlert;
////import model.entity.api.AlertDetail;
//import dao.AlertDAO;
//import integration.cta.traintracker.client.CustomerAlertsClient;
//import model.entity.cta.customeralerts.CTAAlert;
//import model.entity.api.AlertDetail;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Optional;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserAlertProcessorTest {
//
//    @InjectMocks
//    private UserAlertProcessor processor;
//
//    @Mock
//    private CustomerAlertsClient client;
//
//    @Mock
//    private AlertDAO alertDAO;
//
//
//
//    @Test
//    public void process() {
//
//    }
//
//    @Test
//    public void removePreviouslyNotifiedAlerts() {
//        CTAAlert d1 = new CTAAlert(232, null, " osdfsdif j3 ", null, null, null, null, true,false, null,null );
//        CTAAlert d2 = new CTAAlert(4223, "bla bla fbdsf", "!", null, null, null, null, false,false, null,null );
//        CTAAlert d3 = new CTAAlert(49, "bla fdsfsd 124124", "434", null, null, null, null, true,false, null,null );
//        CTAAlert d4 = new CTAAlert(155, "bla bla 4242", "jbsnd", null, null, null, null, false,false, null,null );
//
//        when(alertDAO.findByAlertID(232)).thenReturn(Optional.of(new AlertDetail()));
//        when(alertDAO.findByAlertID(155)).thenReturn(Optional.of(new AlertDetail()));
//
//        CTAAlert[] filtered = processor.removePreviouslyNotifiedAlerts(new CTAAlert[]{d1,d2,d3,d4});
//        assertArrayEquals(filtered, new CTAAlert[]{d2,d3});
//    }
//}