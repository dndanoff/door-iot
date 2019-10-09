package io.github.dndanoff.doormonitor;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.dndanoff.doormonitor.config.AppConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles="test")
public class DoorMonitorApplicationTest {

	@Test
	public void contextLoads() {
	}

}
