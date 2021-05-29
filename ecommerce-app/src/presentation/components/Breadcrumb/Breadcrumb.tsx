import React from "react";
import useBreadCrumb from '../../hooks/useBreadcrumb';
import BreadcrumbView from './BreadcrumbView';

type Props =  {}
const Breadcrumb: React.FC<Props> = () => {
  const locationItems = useBreadCrumb();
  return <BreadcrumbView {...{locationItems}}/>;
};

export default Breadcrumb;