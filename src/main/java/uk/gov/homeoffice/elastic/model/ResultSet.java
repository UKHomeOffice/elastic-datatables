package uk.gov.homeoffice.elastic.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a resultset
 * @param <T>
 */
public class ResultSet<T> {

    /**
     * Total records, after filtering (i.e. the total number of records after filtering has been applied - not
     * just the number of records being returned for this page of data).
     */
    private final int recordsFiltered;

    /**
     * Total records, before filtering (i.e. the total number of records in the database)
     */
    private final int recordsTotal;

    /**
     *
     */
    private final List<T> records = new ArrayList<>();


    public ResultSet(int recordsTotal, int recordsFiltered, List<T> records){
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.records.addAll(records);
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public List<T> getRecords() {
        return records;
    }
}
