import { gql } from 'apollo-server';
import { GraphQLScalarType } from "graphql";

export const LongScalarType = new GraphQLScalarType({
  name: "Long",
  description: "Long custom scalar type",
});

export const LongType = gql`
  scalar Long
`;