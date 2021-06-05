import { ApolloServer } from 'apollo-server';
import resolver from './resolver';
import Types from './schema/types';
import Inputs from './schema/inputs';

const { schemas, resolvers } = resolver;

const server = new ApolloServer({ 
  typeDefs: [ ...Types, ...Inputs , schemas ], 
  resolvers
});

export default server; 