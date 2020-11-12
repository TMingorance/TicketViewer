package Tests;

import com.TicketViewer.Controller.TicketListController;
import com.TicketViewer.Controller.TicketController;
import com.TicketViewer.View.MainPage;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class MainPageController {
    @Mock
    private TicketListController ticketListController;

    @Mock
    private TicketController ticketController;

    @InjectMocks
    private MainPageController mainPageController;


}
