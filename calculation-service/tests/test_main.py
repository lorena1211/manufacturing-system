from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)


def test_health():
    response = client.get("/health")
    assert response.status_code == 200
    assert response.json() == {"status": "ok"}


def test_calculate_endpoint():
    payload = {
        "items": [
            {"product": "Software Licence", "unitPrice": 500000, "amount": 2},
            {"product": "Technical support", "unitPrice": 150000, "amount": 1},
        ]
    }
    response = client.post("/calculate", json=payload)
    assert response.status_code == 200
    data = response.json()
    assert data["subtotal"] == 1150000
    assert data["total"] == data["subtotal"] - data["totalDiscounts"] + data["totalTaxes"]


def test_calculate_with_empty_items():
    response = client.post("/calculate", json={"items": []})
    assert response.status_code == 200
    assert response.json()["total"] == 0