package io.github.marrafon91.planeja_api.infra;

import io.github.marrafon91.planeja_api.dominio.cartao.CartaoRepository;
import io.github.marrafon91.planeja_api.dominio.cartao.model.BandeiraCartao;
import io.github.marrafon91.planeja_api.dominio.cartao.model.CartaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sandbox implements CommandLineRunner {

    @Autowired
    CartaoRepository repository;

    public void salvarCartao() {
        CartaoEntity cartao = new CartaoEntity();
        cartao.setNome("ITAU Personalite");
        cartao.setBandeira(BandeiraCartao.AMERICAN_EXPRESS);

        repository.save(cartao);
    }

    @Override
    public void run(String... args) throws Exception {
        salvarCartao();
    }
}
