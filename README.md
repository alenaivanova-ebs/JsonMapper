# JsonMapper

Input:
{"order_id":"1234567890","order_status":"delivered","delivery":{"delivery_id":"d923jd29j91d1gh6","delivery_time":"2022-05-20T11:50:48Z"},"amount": 6477}
{"order_id":"1234567891","order_status":"delivered","delivery":{"delivery_id":"d923jd29j91d1gh6","delivery_time":"2022-05-20T11:50:48Z"},"amount": 249}
{"order_id":"1234567892","order_status":"delivered","delivery":{"delivery_id":"j93jf923jf23jg9f","delivery_time":"2022-03-20T08:15:00Z"},"amount": 4295}

Output:

Orders should be grouped by delievery

Add total_amount for the delivery

[
  {
    "delivery_id": "j93jf923jf23jg9f",
    "delivery_time": "2022-03-20T08:15:00Z",
    "delivery_status": "delivered",
    "orders": [
      {
        "order_id": "1234567892",
        "amount": 4295
      }
    ],
    "total_amount": 4295
  },
  {
    "delivery_id": "d923jd29j91d1gh6",
    "delivery_time": "2022-05-20T11:50:48Z",
    "delivery_status": "delivered",
    "orders": [
      {
        "order_id": "1234567891",
        "amount": 249
      },
      {
        "order_id": "1234567890",
        "amount": 6477
      }
    ],
    "total_amount": 6726
  }
]
