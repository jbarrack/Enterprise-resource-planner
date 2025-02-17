package com.inventory.Erp.Controller;

import com.inventory.Erp.Services.GoodsReturnedService;
import com.inventory.Erp.model.GoodsReturned;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class GoodsReturnedController {
    @Autowired
    private GoodsReturnedService goodsReturnedService;
    @RequestMapping(value = "/creatingGoodsReturn", method = RequestMethod.POST)
    public ResponseEntity<GoodsReturned> processGoodsReturn(@RequestBody GoodsReturned goodsReturned) {
      GoodsReturned createNewReturn = goodsReturnedService.creatNewRecord(goodsReturned);
      return new ResponseEntity<>(createNewReturn, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/getAllRecords", method = RequestMethod.GET)
    public ResponseEntity<List<GoodsReturned>> goodsReturnList(){
        List<GoodsReturned> getAllList = goodsReturnedService.getGoodsReturnedList();
        return new ResponseEntity<>(getAllList,HttpStatus.OK);
    }
    @RequestMapping(value = "/findrecordById/{id}", method = RequestMethod.GET)
    public ResponseEntity<GoodsReturned> findRecordById(@PathVariable("id")int id){
        GoodsReturned goodsReturned = goodsReturnedService.findGoodsById(id);
        return new ResponseEntity<>(goodsReturned,HttpStatus.OK);
    }
    @RequestMapping(value = "/updateRecord/{id}", method = RequestMethod.POST)
    public ResponseEntity<GoodsReturned> findRecordById(@PathVariable("id")int id,GoodsReturned updateRecords){
        GoodsReturned updateRecordsreturned = goodsReturnedService.updateGoodsReturned(id,updateRecords);
        return new ResponseEntity<>(updateRecordsreturned,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE)
    public void deteleteRecord(int id){
        goodsReturnedService.deleteRecord(id);
    }
    }


