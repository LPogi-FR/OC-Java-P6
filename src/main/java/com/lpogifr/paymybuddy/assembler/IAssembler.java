package com.lpogifr.paymybuddy.assembler;

import java.util.List;

public interface IAssembler<E, M> {
  E fromModelToEntity(M model);

  M fromEntityToModel(E entity);

  List<E> fromModelListToEntityList(List<M> modelList);

  List<M> fromEntityListToModelList(List<E> entityList);
}
