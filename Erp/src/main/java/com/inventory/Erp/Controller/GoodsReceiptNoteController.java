package com.inventory.Erp.Controller;

import com.inventory.Erp.Services.GoodsReceiptNoteService;
import com.inventory.Erp.model.GoodsReceiptNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goodsReceiptNotes")
public class GoodsReceiptNoteController {
    @Autowired
    private GoodsReceiptNoteService goodsReceiptNoteService;
    @PostMapping
    public ResponseEntity<GoodsReceiptNote> createGoodsReceiptNote(@RequestBody GoodsReceiptNote grn) {
        return ResponseEntity.ok(goodsReceiptNoteService.createGoodsReceiptNote(grn)); }
}
