package org.meme.corp;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.meme.corp.database.entity.Event;
import org.meme.corp.database.repository.impl.EventRepository;
import org.meme.corp.util.GraphUtil;

@Slf4j
public class Main {

  public static void main(String[] args) {

    final float[] result = GraphUtil.calculateBalances(new float[][]{
        //     A    B    C    D    E
        /*A*/{100, 100, 100, 100, 100},
        /*B*/{75, 75, 75, 75, 0},
        /*C*/{0, 0, 0, 0, 0},
        /*D*/{0, 0, 0, 0, 0},
        /*E*/{0, 0, 0, 0, 0},
    });
    log.info(Arrays.toString(result));

    EventRepository eventRepository = new EventRepository();
    eventRepository.save(new Event(1L, "New Event"));
  }

}
