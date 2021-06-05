import buildResolvers from "./Resolver";
import ProductResolver from "./ProductResolver";
import CheckoutResolver from "./CheckoutResolver";

export default buildResolvers([ProductResolver, CheckoutResolver]);