package groovy.ru.merann.bepopov.autoshowroom.server.service

import org.junit.Test
import org.junit.jupiter.api.BeforeAll
import ru.merann.bopopov.autoshowroom.server.model.Make
import ru.merann.bopopov.autoshowroom.server.repository.MakeRepository
import ru.merann.bopopov.autoshowroom.server.service.MakeService
import ru.merann.bopopov.autoshowroom.server.service.impl.MakeServiceImpl

import static org.mockito.Mockito.*;

class MakeServiceTest {

    MakeService makeService
    List<Make> existingMakes
    List<Make> searchResultMakes
    String searchTextForExistingMakes

    @BeforeAll
    void initialize() {
        MakeRepository makeRepository = mock(MakeRepository.class)
        when(makeRepository.findAllNameByName(searchTextForExistingMakes)).thenReturn(existingMakes)
        makeService = new MakeServiceImpl(makeRepository)
    }

    @Test
    void testSearchByText() {

    }

}
