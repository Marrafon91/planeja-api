package io.github.marrafon91.planeja_api.dominio.dashboard;

import io.github.marrafon91.planeja_api.dominio.dashboard.dto.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping
    public Dashboard obterDashboardMesAtual() {
        return service.obterDashboardMesAtual();
    }
}
