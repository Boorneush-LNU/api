package com.cucumbercraft.Models;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;

@Data
public class ModalData {
    @ExcelCellName("Modal name")   // These names will be in the Excel
    private String modalName;

    @ExcelCellName("Modal header")
    private String modalHdr;

    @ExcelCellName("Modal Content")
    private String modalContent;

    @ExcelCellName("Modal Primary Button")
    private String modalPrimaryButton;

    @ExcelCellName("Modal Secondary Button")
    private String modalSecondaryButton;
}
