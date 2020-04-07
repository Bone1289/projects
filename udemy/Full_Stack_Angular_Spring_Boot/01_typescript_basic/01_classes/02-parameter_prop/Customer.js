"use strict";
var Customer = /** @class */ (function () {
    function Customer(_fistName, _lastName) {
        this._fistName = _fistName;
        this._lastName = _lastName;
    }
    Object.defineProperty(Customer.prototype, "fistName", {
        get: function () {
            return this._fistName;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Customer.prototype, "firstName", {
        set: function (fistName) {
            this._fistName = fistName;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Customer.prototype, "lastName", {
        get: function () {
            return this._lastName;
        },
        set: function (value) {
            this._lastName = value;
        },
        enumerable: true,
        configurable: true
    });
    return Customer;
}());
var customer = new Customer("Bob", "Dinamite");
console.log("Customer name is " + customer.fistName + " " + customer.lastName);
