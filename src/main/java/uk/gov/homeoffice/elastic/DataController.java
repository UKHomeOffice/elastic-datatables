package uk.gov.homeoffice.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.gov.homeoffice.elastic.dataTableModel.DataTablesRequest;
import uk.gov.homeoffice.elastic.dataTableModel.DataTablesResponse;
import uk.gov.homeoffice.elastic.model.Record;
import uk.gov.homeoffice.elastic.model.ResultSet;
import uk.gov.homeoffice.elastic.provider.ResultSetProvider;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    ResultSetProvider<Record> dataProvider;

    @ResponseBody
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public DataTablesResponse<Record> find(@RequestBody DataTablesRequest request) {
        // The argument 'request' contains search and paging parameters.

        DataTablesResponse<Record> response = new DataTablesResponse<>();
        response.setDraw(request.getDraw());

        //get the data from the backend
        ResultSet<Record> data = dataProvider.getResultSet(request.getSearch(), request.getStart(), request.getLength());

        response.setData(trim(data.getRecords()));
        response.setRecordsTotal(data.getRecordsTotal());
        response.setRecordsFiltered(data.getRecordsFiltered());

        return response;
    }

    private List<Record> trim(List<Record> records) {
        List<Record> l = new ArrayList<>();
        for (Record rec : records) {
            l.add(trimmed(rec));
        }
        return l;
    }


    private Record trimmed(Record rec) {
        Record r = new Record();
        //IMP dont trim property 1 as its the id used to lookup the cache
        r.setProperty1(rec.getProperty1());
        r.setProperty2(rec.getProperty2());
        r.setProperty3(rec.getProperty3());
        r.setProperty4(rec.getProperty4());
        r.setProperty5(rec.getProperty5());
        r.setProperty6(tp(rec.getProperty6()));
        r.setProperty7(tp(rec.getProperty7()));
        r.setProperty8(tp(rec.getProperty8()));
        r.setProperty9(tp(rec.getProperty9()));
        r.setProperty10(tp(rec.getProperty10()));
        r.setProperty11(tp(rec.getProperty11()));
        r.setProperty12(tp(rec.getProperty12()));
        return r;
    }

    private String tp(String rec){
        if(rec.length()>12)
        {
            return rec.substring(0, 12) + "..";
        }
        return rec;
    }


    @ResponseBody
    @RequestMapping(value = "/record/{id}", method = RequestMethod.GET)
    public Record find(@PathVariable String id) {
        //get the data from the backend
        Record data = dataProvider.uniqueRecord(id);
        return data;
    }

}