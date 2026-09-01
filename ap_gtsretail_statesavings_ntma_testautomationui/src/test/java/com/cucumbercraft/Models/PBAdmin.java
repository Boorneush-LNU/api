package com.cucumbercraft.Models;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;

@Data
public class PBAdmin {

    @ExcelCellName("Date/Time")
    private String date;

    @ExcelCellName("Card Type")
    private String cardType;

    @ExcelCellName("Card No")
    private String cardNumber;

    @ExcelCellName("Name")
    private String name;

    @ExcelCellName("Auth Code")
    private String authCode;

    @ExcelCellName("Cart Payment No.")
    private String paymetRefNo;

    @ExcelCellName("Value")
    private String value;
}
