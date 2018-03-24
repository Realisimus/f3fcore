package f3f.core.tools;

import f3f.data_connector.entity.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultTools {

    public List<Float> getTimes(List<Result> results) {
        List<Float> times = new ArrayList<>();
        for (Result result : results) {
            times.add(result.getTime());
        }
        return times;
    }

    public Float minTime(List<Float> times) {
        Float minTime = times.get(0);
        for (Float time : times) {
            if (time < minTime) {
                minTime = time;
            }
        }
        return minTime;
    }
}
