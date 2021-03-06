package uk.gov.homeoffice.elastic.provider;

import uk.gov.homeoffice.elastic.dataTableModel.DataTablesSearch;
import uk.gov.homeoffice.elastic.model.ResultSet;

import java.util.List;

public interface ResultSetProvider<T> {

    /**
     *
     * @return
     */
    List<String> getColumns();

    /**
     * Search for records
     *
     * @param search the search details
     * @param offset offset and length
     * @param length
     * @return
     */
    ResultSet<T> getResultSet(DataTablesSearch search, int offset, int length);

    /**
     * Search for a unique record
     *
     * @param key
     * @return
     */
    T uniqueRecord(String key);

}
