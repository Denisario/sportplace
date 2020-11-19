package by.shestopalov.sportplace;

import by.shestopalov.sportplace.entity.Place;
import by.shestopalov.sportplace.repository.PlaceRepository;
import by.shestopalov.sportplace.service.impl.EventServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnitTests {

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private PlaceRepository placeRepository;

    @Test
    public void getNotNullData() throws Exception {
        Assert.assertNotNull(eventService.getEventById(50L));
    }

    @Test
    public void getVariableFromServiceEqual() throws Exception {
        Assert.assertTrue(eventService.getEventById(1L).getName().equals("asdas"));
    }

    @Test
    public void testRepos(){
        Assert.assertNotNull(placeRepository.getAllCountries());
    }

    @Test
    public void testReposSecond(){
        Assert.assertTrue(placeRepository.findPlaceByName("321312").isEmpty());
    }
}
