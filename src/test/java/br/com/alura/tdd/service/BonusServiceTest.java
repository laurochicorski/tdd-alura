package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BonusServiceTest {

    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {
        BonusService service = new BonusService();

//      1ª opção
//        assertThrows(IllegalArgumentException.class
//                , () -> service.calcularBonus(new Funcionario("Lauro", LocalDate.now(), new BigDecimal("25000"))));

//        2ª opção
        try{
            service.calcularBonus(new Funcionario("Lauro", LocalDate.now(), new BigDecimal("25000")));
            fail("Nao disparou exceção para funcionario com salario maior que 10.000,00");
        }catch (IllegalArgumentException e) {
            assertEquals("Funcionario com salario maior que 10.000,00 não pode receber bonus", e.getMessage());
        }

    }

    @Test
    void bonusDeveriaSer10PorCentoDoSalario() {
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Lauro", LocalDate.now(), new BigDecimal("2500")));

        assertEquals(new BigDecimal("250.00"), bonus);
    }

    @Test
    void bonusDeveriaSer10PorCentoParaSalarioDeExatamente10000() {
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Lauro", LocalDate.now(), new BigDecimal("10000")));

        assertEquals(new BigDecimal("1000.00"), bonus);
    }
}
