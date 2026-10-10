import pytest
from app.models import ItemInvoice
from app.calculation import calculate_invoice


def test_simple_calculus_test_without_discount():
    items = [ItemInvoice(product="Licence", unitPrice=500000, amount=1)]
    result = calculate_invoice(items)

    assert result.subtotal == 500000
    assert result.totalDiscounts == 0
    assert result.totalTaxes == 95000  
    assert result.total == 595000


def test_volume_discount_calculation():
    items = [ItemInvoice(product="Licence", unitPrice=1000000, amount=1)]
    result = calculate_invoice(items)

    assert result.totalDiscounts == 50000  # 5% de 1,000,000
    assert result.total == 1130500  # (1,000,000 - 50,000) * 1.19


def test_empty_list():
    result = calculate_invoice([])
    assert result.subtotal == 0
    assert result.total == 0


def test_negative_price_launches_error():
    with pytest.raises(Exception):
        ItemInvoice(product="Test", unitPrice=-100, amount=1)


def test_amount_zero_launch_error():
    with pytest.raises(Exception):
        ItemInvoice(product="Test", unitPrice=100, amount=0)