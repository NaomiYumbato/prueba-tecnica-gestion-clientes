CREATE OR REPLACE FUNCTION rpt_totales_clientes_por_tipo()
RETURNS TABLE (
    tipo_cliente TEXT,
    total BIGINT
)
AS $$
BEGIN
    RETURN QUERY
    SELECT 
        t.descripcion::TEXT AS tipo_cliente,
        COUNT(c.id)::BIGINT AS total
    FROM cliente c
    JOIN tipo_cliente t ON t.id = c.id_tipo
    GROUP BY t.descripcion
    ORDER BY total DESC;
END;
$$ LANGUAGE plpgsql;