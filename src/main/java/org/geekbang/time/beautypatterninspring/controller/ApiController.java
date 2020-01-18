package org.geekbang.time.beautypatterninspring.controller;

import org.geekbang.time.beautypatterninspring.entity.VirtualWalletTransactionEntity;
import org.geekbang.time.beautypatterninspring.service.VirtualWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private VirtualWalletService virtualWalletService;

    @RequestMapping("/transaction/list/{walletId}")
    public List<VirtualWalletTransactionEntity> getTransactionList(@PathVariable String walletId) {
        return virtualWalletService.getTransactionList(walletId);
    }

}
