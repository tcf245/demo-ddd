package cn.cyanbean.controller;

import cn.cyanbean.application.TradeManager;
import cn.cyanbean.controller.dto.TradeDTO;
import cn.cyanbean.domain.model.TradeRecord;
import cn.cyanbean.domain.repository.TradeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/trade")
public class TradeController {
    private final TradeManager tradeManager;
    private final TradeRepository tradeRepository;

    public TradeController(TradeManager tradeManager, TradeRepository tradeRepository) {
        this.tradeManager = tradeManager;
        this.tradeRepository = tradeRepository;
    }

    @PostMapping("/recharge")
    public TradeDTO recharge(@RequestBody TradeDTO tradeDTO) {
        return TradeDTO.from(tradeManager.recharge(tradeDTO.to()));
    }

    @PostMapping("/consume")
    public TradeDTO consume(@RequestBody TradeDTO tradeDTO) {
        return TradeDTO.from(tradeManager.consume(tradeDTO.to()));
    }

    @GetMapping()
    public List<TradeDTO> findAll() {
        return tradeRepository.findAll()
                .map(records -> records.stream().map(TradeDTO::from).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

}
