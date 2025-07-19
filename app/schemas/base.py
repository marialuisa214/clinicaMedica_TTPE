"""
Schemas base usando Pydantic
"""

from datetime import datetime
from typing import Optional
from pydantic import BaseModel, ConfigDict


class BaseSchema(BaseModel):
    """Schema base para todas as entidades"""
    
    model_config = ConfigDict(from_attributes=True)


class BaseResponseSchema(BaseSchema):
    """Schema base para responses com campos comuns"""
    
    id: int
    created_at: datetime
    updated_at: Optional[datetime] = None 