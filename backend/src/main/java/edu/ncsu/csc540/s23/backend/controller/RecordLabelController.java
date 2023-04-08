package edu.ncsu.csc540.s23.backend.controller;

import edu.ncsu.csc540.s23.backend.model.RecordLabel;
import edu.ncsu.csc540.s23.backend.service.RecordLabelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("record-label")
public class RecordLabelController {
    private RecordLabelService recordLabelService;

    public RecordLabelController(RecordLabelService recordLabelService) { this.recordLabelService = recordLabelService; }

    @GetMapping("/all")
    public List<RecordLabel> getAllRecordLabels() { return recordLabelService.getAllRecordLabels(); }

    @PostMapping("/add")
    public Long addRecordLabel(@RequestBody RecordLabel recordLabel) { return recordLabelService.createNewRecordLabel(recordLabel); }
}
