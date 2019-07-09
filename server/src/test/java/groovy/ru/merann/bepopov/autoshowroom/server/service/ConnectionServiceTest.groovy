package groovy.ru.merann.bepopov.autoshowroom.server.service

import org.junit.Test
import org.junit.Assert
import org.junit.jupiter.api.BeforeAll
import ru.merann.bopopov.autoshowroom.server.model.Client
import ru.merann.bopopov.autoshowroom.server.repository.ClientRepository
import ru.merann.bopopov.autoshowroom.server.service.impl.ConnectionServiceImpl

import static org.mockito.Mockito.*;
import ru.merann.bopopov.autoshowroom.server.service.ConnectionService

class ConnectionServiceTest {

    ConnectionService connectionService
    Client client
    String nameOfExistingClient
    Long idOfExistingClient
    String nameOfNotExistingClient

    @BeforeAll
    void initialize() {
        ClientRepository clientRepository = mock(ClientRepository.class)
        nameOfExistingClient = "Hector"
        nameOfNotExistingClient = "John"
        idOfExistingClient = 1L
        client = new Client()
        client.setId(idOfExistingClient)
        client.setName(nameOfExistingClient)
        when(clientRepository.findOneByName(nameOfExistingClient)).thenReturn(client)
        when(clientRepository.findOneById(idOfExistingClient)).thenReturn(client)
        when(clientRepository.findByName(nameOfExistingClient))thenReturn(idOfExistingClient)
        connectionService = new ConnectionServiceImpl(clientRepository)
    }

    @Test
    void testGetClientIdForExistingClient() {
        Assert.assertEquals(connectionService.getClientId(nameOfExistingClient), idOfExistingClient)
    }

    @Test
    void testGetClientForExistingClient() {
        Assert.assertEquals(connectionService.getClient(nameOfExistingClient), client)
    }

    @Test
    void testGetClientIdForNotExistingClientName() {
        Assert.assertEquals(connectionService.getClientId(idOfNotExistingClient), null)
    }

    @Test
    void testGetClientForNotExistingClientName() {
        Assert.assertEquals(connectionService.getClient(idOfNotExistingClient), null)
    }

    @Test
    void testGetClientForRussianClientName() {

    }

    @Test
    void testGetClientIdForRussianClientName() {

    }

}
