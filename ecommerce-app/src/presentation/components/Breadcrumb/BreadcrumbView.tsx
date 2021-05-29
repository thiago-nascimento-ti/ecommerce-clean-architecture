import React from "react";
import { Breadcrumb } from "antd";
import { styled } from "./BreadcrumbStyled";
import { Link } from 'react-router-dom';

type Props =  {
  locationItems: Array<TLocation>
}
const BreadcrumbView: React.FC<Props> = ({ locationItems }) => {
  return (
    <Breadcrumb style={styled.breadcrumb}>
      {locationItems.map(({name, uri}, key) => {
        return (
          <Breadcrumb.Item key={key}>
            <Link to={uri}>{name}</Link>
          </Breadcrumb.Item>
        )
      })}
    </Breadcrumb>
  );
};

export type TLocation = {
  name: string
  uri: string
}

export default BreadcrumbView;
