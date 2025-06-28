package ar.edu.utn.tup.pro.iii.controllers;

import ar.edu.utn.tup.pro.iii.dtos.DummyDto;
import ar.edu.utn.tup.pro.iii.models.Dummy;
import ar.edu.utn.tup.pro.iii.services.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dummy")
public class DummyController {

    private DummyService dummyService;

    @GetMapping("")
    public ResponseEntity<DummyDto> getDummyList() {
        List<Dummy> dummyList = dummyService.getDummyList();
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DummyDto> getDummy(@PathVariable Long id) {
        Dummy dummy = dummyService.getDummy(id);
        return null;
    }

    @PostMapping("")
    public ResponseEntity<DummyDto> postDummy(@RequestBody DummyDto dto) {
        Dummy dummy = dummyService.createDummy(null);
        return null;
    }

    @PutMapping("")
    public ResponseEntity<DummyDto> updateDummy(@RequestBody DummyDto dto) {
        Dummy dummy = dummyService.updateDummy(null);
        return null;
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteDummy(@RequestBody DummyDto dto) {
        dummyService.deleteDummy(null);
        return null;
    }

}
