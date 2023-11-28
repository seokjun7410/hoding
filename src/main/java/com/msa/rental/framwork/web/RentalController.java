package com.msa.rental.framwork.web;

import com.msa.rental.application.usecase.*;
import com.msa.rental.framwork.web.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RentalController {

    private final RentItemUsecase rentItemUsecase;
    private final ReturnItemUsecase returnItemUsecase;
    private final CreateRentalCardUsecase createRentalCardUsecase;
    private final OverdueItemUsercase overdueItemUsercase;
    private final ClearOverdueItemUsecase clearOverdueItemUsecase;
    private final InquiryUsecase inquiryUsecase;


    @ApiOperation(value = "도서카드 생성",notes = "사용자정보 -> 도서카드정보")
    @PostMapping("/RentalCard/")
    public ResponseEntity<RentalCardOutputDto> creatRentalCard(@RequestBody UserInputDto userInputDto)
    {
        RentalCardOutputDto createRentalCard = createRentalCardUsecase.createRentalCard(userInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createRentalCard);
    }

    @ApiOperation(value = "도서카드 조회",notes = "사용자정보(id) -> 도서카드정보")
    @GetMapping("/RentalCard/{userId}")
    public ResponseEntity<RentalCardOutputDto> getRentalCard(@PathVariable String userId){
        Optional<RentalCardOutputDto> rentalCard = inquiryUsecase.getRentalCard(new UserInputDto(userId, ""));
        return ResponseEntity.ok(rentalCard.get());
    }
    @ApiOperation(value = "대여도서목록 조회",notes = "사용자정보(id) -> 대여도서목록 조회")
    @GetMapping("/RentalCard/{userId}/rentbook")
    public ResponseEntity<List<RentItemOutputDto>> getAllRentItem(@PathVariable String userId){
        Optional<List<RentItemOutputDto>> allRentItem = inquiryUsecase.getAllRentItem(new UserInputDto(userId, ""));
        return ResponseEntity.ok(allRentItem.get());
    }

    @ApiOperation(value = "반납도서목록 조회",notes = "사용자정보(id) -> 반납도서목록 조회")
    @GetMapping("/RentalCard/{userId}/returnbook")
    public ResponseEntity<List<ReturnItemOutputDto>> getAllReturnItem(@PathVariable String userId){
        Optional<List<ReturnItemOutputDto>> allReturnItem = inquiryUsecase.getAllReturnItem(new UserInputDto(userId, ""));
        return ResponseEntity.ok(allReturnItem.get());
    }

    @ApiOperation(value = "대여기능",notes = "사용자정보,아이템정보1 -> 도서카드정보 ")
    @PostMapping("/RentalCard/rent")
    public ResponseEntity<RentalCardOutputDto> rentItem(@RequestBody UserItemInputDto userItemInputDto) throws Exception {
        RentalCardOutputDto rentalCardOutputDto = rentItemUsecase.rentItem(userItemInputDto);
        return ResponseEntity.ok(rentalCardOutputDto);
    }

    @ApiOperation(value = "반납기능", notes = "사용자정보,아이템정보1 -> 도서카드정보 ")
    @PostMapping("/RentalCard/return")
    public ResponseEntity<RentalCardOutputDto> returnItem(@RequestBody UserItemInputDto userItemInputDto) throws Exception {
        RentalCardOutputDto rentalCardOutputDto = returnItemUsecase.returnItem(userItemInputDto);
        return ResponseEntity.ok(rentalCardOutputDto);
    }

    @ApiOperation(value = "연체기능", notes = "사용자정보,아이템정보1 -> 도서카드정보 ")
    @PostMapping("/RentalCard/overdue")
    public ResponseEntity<RentalCardOutputDto> overdueItem(@RequestBody UserItemInputDto userItemInputDto) throws Exception {
        RentalCardOutputDto rentalCardOutputDto = overdueItemUsercase.overDueItem(userItemInputDto);
        return ResponseEntity.ok(rentalCardOutputDto);
    }

    @ApiOperation(value = "연체해제기능", notes = "사용자정보,포인트 -> 도서카드정보 ")
    @PostMapping("/RentalCard/clearoverdue")
    public ResponseEntity<RentalResultOutputDto> clearOverdueItem(@RequestBody ClearOverDueInputDto clearOverDueInputDto) throws Exception {
        RentalResultOutputDto rentalResultOuputDTO = clearOverdueItemUsecase.clearOverdue(clearOverDueInputDto);
        return ResponseEntity.ok(rentalResultOuputDTO);
    }

}
