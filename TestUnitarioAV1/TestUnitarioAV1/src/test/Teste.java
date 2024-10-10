package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void testCriacaoItemValidos() {
        Item item = new Item("Item X", 200.0, 15);
        assertEquals("Item X", item.getNome());
        assertEquals(200.0, item.getPreco(), 0.01);
        assertEquals(15, item.getEstoque());
    }

    @Test
    public void testCriacaoItemPrecoNegativo() {
        Item item = new Item("Item Y", -30.0, 20);
        assertFalse("Preço deve ser positivo", item.getPreco() >= 0);
    }

    @Test
    public void testCriacaoItemEstoqueNegativo() {
        Item item = new Item("Item Z", 80.0, -5);
        assertFalse("Estoque deve ser positivo", item.getEstoque() >= 0);
    }

    @Test
    public void testAlteracaoNomeItem() {
        Item item = new Item("Item W", 100.0, 10);
        item.setNome("Item W Modificado");
        assertEquals("Item W Modificado", item.getNome());
    }

    @Test
    public void testAlteracaoPrecoItemValorValido() {
        Item item = new Item("Item V", 120.0, 8);
        item.setPreco(150.0);
        assertEquals(150.0, item.getPreco(), 0.01);
    }

    @Test
    public void testAlteracaoEstoqueItemPositivo() {
        Item item = new Item("Item U", 90.0, 12);
        item.setEstoque(18);
        assertEquals(18, item.getEstoque());
    }

    @Test
    public void testAlteracaoPrecoItemValorNegativo() {
        Item item = new Item("Item T", 75.0, 10);
        item.setPreco(35.0);
        assertTrue("Preço deve ser positivo", item.getPreco() >= 0);
    }

    @Test
    public void testVendaQuantidadeMenorQueEstoque() {
        Item item = new Item("Item S", 60.0, 15);
        Transacao venda = new Transacao(item, 7);
        assertTrue(venda.realizarTransacao());
        assertEquals(8, item.getEstoque());
    }

    @Test
    public void testVendaQuantidadeIgualAoEstoque() {
        Item item = new Item("Item R", 70.0, 10);
        Transacao venda = new Transacao(item, 10);
        assertTrue(venda.realizarTransacao());
        assertEquals(0, item.getEstoque());
    }

    @Test
    public void testVendaQuantidadeMaiorQueEstoque() {
        Item item = new Item("Item Q", 45.0, 5);
        Transacao venda = new Transacao(item, 10);
        assertFalse(venda.realizarTransacao());
    }

    @Test
    public void testCalculoTotalTransacao() {
        Item item = new Item("Item P", 55.0, 10);
        Transacao venda = new Transacao(item, 3);
        venda.realizarTransacao();
        assertEquals(165.0, venda.getTotalTransacao(), 0.01);
    }

    @Test
    public void testAumentoEstoqueItemPosVenda() {
        Item item = new Item("Item O", 60.0, 5);
        item.aumentarEstoque(10);
        assertEquals(15, item.getEstoque());
    }

    @Test
    public void testDiminuicaoEstoqueItemTransacaoBemSucedida() {
        Item item = new Item("Item N", 90.0, 8);
        Transacao venda = new Transacao(item, 4);
        venda.realizarTransacao();
        assertEquals(4, item.getEstoque());
    }

    @Test
    public void testTransacaoItemInexistente() {
        Item item = null;
        Transacao venda = new Transacao(item, 6);
        assertThrows(NullPointerException.class, venda::realizarTransacao);
    }

    @Test
    public void testCriacaoTransacaoQuantidadeNegativa() {
        Item item = new Item("Item M", 75.0, 20);
        Transacao venda = new Transacao(item, 6);
        assertTrue(venda.realizarTransacao());
    }

    @Test
    public void testAlteracaoEstoqueTransacaoComEstoqueInsuficiente() {
        Item item = new Item("Item L", 85.0, 5);
        Transacao venda = new Transacao(item, 10);
        venda.realizarTransacao();
        assertEquals(5, item.getEstoque());
    }

    @Test
    public void testCriacaoVariosItensTransacoesEstoqueCompartilhado() {
        Item item1 = new Item("Item K1", 35.0, 10);
        Item item2 = new Item("Item K2", 40.0, 10);
        Transacao venda1 = new Transacao(item1, 5);
        Transacao venda2 = new Transacao(item2, 5);
        assertTrue(venda1.realizarTransacao());
        assertTrue(venda2.realizarTransacao());
        assertEquals(5, item1.getEstoque());
        assertEquals(5, item2.getEstoque());
    }

    @Test
    public void testTotalTransacaoPrecoAlteradoAntesVenda() {
        Item item = new Item("Item J", 60.0, 8);
        item.setPreco(75.0);
        Transacao venda = new Transacao(item, 3);
        venda.realizarTransacao();
        assertEquals(225.0, venda.getTotalTransacao(), 0.01);
    }

    @Test
    public void testVendaEstoqueInicialZero() {
        Item item = new Item("Item I", 50.0, 0);
        Transacao venda = new Transacao(item, 2);
        assertFalse(venda.realizarTransacao());
    }

    @Test
    public void testAumentoEstoqueReposicaoTransacaoPositiva() {
        Item item = new Item("Item H", 70.0, 0);
        item.aumentarEstoque(7);
        Transacao venda = new Transacao(item, 5);
        assertTrue(venda.realizarTransacao());
        assertEquals(2, item.getEstoque());
    }
}
