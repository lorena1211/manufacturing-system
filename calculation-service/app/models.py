from pydantic import BaseModel, Field
from typing import List


class ItemInvoice(BaseModel):
    product: str
    unitPrice: float = Field(gt=0, description="It must be greater than 0")
    amount: int = Field(gt=0, description="It must be greater than 0")


class RequestCalculation(BaseModel):
    items: List[ItemInvoice]


class TaxDetails(BaseModel):
    type: str
    percentage: float
    value: float


class ResponseCalculation(BaseModel):
    subtotal: float
    totalTaxes: float
    totalDiscounts: float
    total: float
    detailTaxes: List[TaxDetails]