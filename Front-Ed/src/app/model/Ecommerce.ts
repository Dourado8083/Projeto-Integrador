import { Business } from "./Business";
import { Product } from "./Product";

export class Ecommerce{

    public ecommerceId: number;
	public ecommerceName: string;
	public ecommercePic: string;
	public ecommerceHeader: string;
	public businessOn: Business;
	public productList: Product[];

}