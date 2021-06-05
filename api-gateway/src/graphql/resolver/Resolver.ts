import { gql, IResolvers } from "apollo-server";
import { DocumentNode } from "graphql";
import { LongScalarType } from "../schema/types/LongScalarType";

export default function buildResolvers(resolvers: Array<Resolver>): GraphqlResolvers {
  let graphqlResolvers: any = {
    Long: LongScalarType,
  };

  const { Query, queriesSchema } = getQueries(resolvers);
   if (Query) {
     graphqlResolvers.Query = Query;
  }

  const { Mutation, mutationsSchema } = getMutations(resolvers);
  if (Mutation) {
    graphqlResolvers.Mutation = Mutation;
  }

  const schemas = gql`
    ${queriesSchema ?`
      type Query {
        ${queriesSchema}
      }
    ` : ""
    }
    ${mutationsSchema ? `
      type Mutation {
        ${mutationsSchema}
      }
    ` : ""}
  `

  return {schemas, resolvers: graphqlResolvers}
}

function getQueries(resolvers: Array<Resolver>) {
  const queries = resolvers
    .filter(onlyResolverWithQueries);

  if (queries.length === 0) {
    return {};
  }

  const Query = queries 
    .reduce(contactQueries, {});
  const schema = queries
    .reduce(concatQuerySchema, ``);

  return { Query, queriesSchema: schema };
}

function getMutations(resolvers: Array<Resolver>) {
  const mutations = resolvers
    .filter(onlyResolverWithMutations);

  if (mutations.length === 0) {
    return {};
  }

  const Mutation = mutations.reduce(contactMutation, {});
  const schema = mutations
    .reduce(concatMutationSchema, ``);

  return { Mutation, mutationsSchema: schema };
}

function concatMutationSchema(schema: string, { mutations }: Resolver) {
  if (schema === "") {
    return mutations!.schema;
  }
  return (`${schema}\n ${mutations!.schema}`)
}

function concatQuerySchema(schema: string, { queries }: Resolver) {
  if (schema === "") {
    return queries!.schema;
  }
  return (`${schema}\n ${queries!.schema}`)
}

function contactMutation(mutation: Object, { mutations }: Resolver) {
  return {...mutation, ...mutations!.mutation};
}

function contactQueries(query: Object, { queries }: Resolver) {
  return {...query, ...queries!.query};
}

function onlyResolverWithQueries(resolver: Resolver) {
  return resolver.queries;
}

function onlyResolverWithMutations(resolver: Resolver) {
  return resolver.mutations;
}

export type GraphqlResolvers = {
  resolvers: IResolvers<any, any>
  schemas: DocumentNode
}

export type Resolver = {
  mutations: Mutations | null,
  queries: Queries | null
}

export type Queries = {
  query: Object
  schema: string
}

export type Mutations = {
  mutation: Object
  schema: string
}