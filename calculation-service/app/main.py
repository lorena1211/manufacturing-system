from fastapi import FastAPI
from app.models import RequestCalculation, ResponseCalculation
from app.calculation import calculate_invoice

app = FastAPI(
    title="Billing Calculation Service",
    description="Calculate taxes and discounts for items on an invoice",
    version="1.0.0",
)


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/calculate", response_model=ResponseCalculation)
def calculate(request: RequestCalculation):
    return calculate_invoice(request.items)