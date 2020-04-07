class Customer {
    private _fistName: string;
    private _lastName: string;

    constructor(firstName: string, lastName: string) {
        this._fistName = firstName;
        this._lastName = lastName;
    }

    public get fistName(): string{
        return this._fistName;
    }

    public set firstName(fistName: string){
        this._fistName = fistName;
    }
    
    public get lastName(): string {
        return this._lastName;
    }
    public set lastName(value: string) {
        this._lastName = value;
    }
}

let customer = new Customer("Bob", "Dinamite");
console.log(`Customer name is ${customer.fistName} ${customer.lastName}`)