from app.models import ItemInvoice, ResponseCalculation, TaxDetails

VAT_PERCENTAGE = 19.0
VOLUME_DISCOUNT_THRESHOLD = 1000000
VOLUME_DISCOUNT_PERCENTAGE = 5.0


def calculate_subtotal_item(item: ItemInvoice) -> float:
    return round(item.unitPrice * item.amount, 2)


def calculate_invoice(items: list[ItemInvoice]) -> ResponseCalculation:
    subtotal = round(sum(calculate_subtotal_item(item) for item in items), 2)

    discount = 0.0
    if subtotal >= VOLUME_DISCOUNT_THRESHOLD:
        discount = round(subtotal * (VOLUME_DISCOUNT_PERCENTAGE / 100), 2)

    taxable_base = subtotal - discount
    vat = round(taxable_base * (VAT_PERCENTAGE / 100), 2)

    total = round(taxable_base + vat, 2)

    return ResponseCalculation(
        subtotal=subtotal,
        totalTaxes=vat,
        totalDiscounts=discount,
        total=total,
        detailTaxes=[
            TaxDetails(type="VAT", percentage=VAT_PERCENTAGE, value=vat)
        ],
    )