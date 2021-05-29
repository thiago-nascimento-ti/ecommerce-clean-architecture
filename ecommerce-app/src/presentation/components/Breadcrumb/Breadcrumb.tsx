import React from "react";
import { useLocation } from 'react-router-dom';
import { Location } from 'history';
import BreadcrumbView, { TLocation } from "./BreadcrumbView";

type Props =  {}
const Breadcrumb: React.FC<Props> = () => {
  const location = useLocation();

  const locationItems: Array<TLocation> = [];
  addLocationItemToHome(locationItems);
  addLocationItemToProduct(locationItems, location);

  return <BreadcrumbView {...{locationItems}}/>;
};

export default Breadcrumb;

function addLocationItemToHome(locationItems: Array<TLocation>): void {
  locationItems.push({name: "home", uri: "/"})
}

function addLocationItemToProduct(locationItems: Array<TLocation>, location: Location): void {
  const locations = location.pathname.split("/");
  if (locations.some((path: string) => path === "produto")) {
    const [, , id ]  = locations;
    locationItems.push({
      name: `produto - ${id}`,
      uri: location.pathname
    })
  }
}