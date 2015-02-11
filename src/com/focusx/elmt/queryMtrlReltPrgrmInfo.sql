SELECT TOP 1000 m.*
  FROM [gialweixin].[dbo].[gv_material] m
  left join [gv_programme_detail] d on d.material_id=m.material_id
  inner join [gv_programme] p on p.progarmme_id=d.programme_id and p.logicdel is null
  where m.material_id=@id  