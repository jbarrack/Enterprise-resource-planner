package com.inventory.Erp.ExeceptionsHandler;

public class SupplierDetailsAlreadyExist extends RuntimeException {
    public SupplierDetailsAlreadyExist(String supplierExist) {
        super(supplierExist);
    }
}
