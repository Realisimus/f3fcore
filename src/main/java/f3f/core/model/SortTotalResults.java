package f3f.core.model;

import f3f.data_connector.entity.TotalResult;

import java.util.Comparator;

public class SortTotalResults {

    public static Comparator<TotalResult> SORT_BY_SCORE = new Comparator<TotalResult>() {
        @Override
        public int compare(TotalResult o1, TotalResult o2) {
            return o2.getScore().compareTo(o1.getScore());
        }
    };
}
